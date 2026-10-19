package net.engineeringdigest.journalApp.cron;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.engineeringdigest.journalApp.Schedular.UserSchedular;

@SpringBootTest 
public class UserScehdulerTest {
    @Autowired 
    private UserSchedular userSchedular;

    @Test
    public void testFetchUserAndSendSAMail(){
        userSchedular.fetchUsersAndSendSaMail();
    }
}
