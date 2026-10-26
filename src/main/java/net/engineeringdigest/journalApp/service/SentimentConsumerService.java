package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import net.engineeringdigest.journalApp.model.SentimentData;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "sentiment-analysis",
    autoStartup = "${app.kafka.enabled:false}", groupId = "sentiment-analysis-consumer")
    public void consume(SentimentData sentimentData) {
        sendEmail(sentimentData);
         System.out.println("===== KAFKA MESSAGE RECEIVED =====");
    System.out.println("Email: " + sentimentData.getEmail());
    System.out.println("Sentiment: " + sentimentData.getSentiment());
    }

    public void sendEmail(SentimentData sentimentData) {
        emailService.sendEmail(sentimentData.getEmail(),
                "Sentiment for previous week", sentimentData.getSentiment());
    }
}
