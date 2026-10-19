package net.engineeringdigest.journalApp.Schedular;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.SentimentEnum;
import net.engineeringdigest.journalApp.repostiory.UserRepositoryImp;
import net.engineeringdigest.journalApp.service.EmailService;

@Component
public class UserSchedular {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserRepositoryImp userRepsitory;

    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepsitory.getUserforSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            if (journalEntries == null || journalEntries.isEmpty()) {
                continue;
            }
            List<SentimentEnum> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<SentimentEnum, Integer> sentimentCounts = new HashMap<>();
            for (SentimentEnum sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            SentimentEnum mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<SentimentEnum, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
