package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    
    @Autowired 
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("myemail@gmail.com","Testing java mail sender functionality","Its only for test");
    }
}
