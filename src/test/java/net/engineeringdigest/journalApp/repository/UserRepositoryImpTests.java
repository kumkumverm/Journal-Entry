package net.engineeringdigest.journalApp.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.engineeringdigest.journalApp.repostiory.UserRepositoryImp;

@SpringBootTest
public class UserRepositoryImpTests {
    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Disabled
    @Test
    public void testSaveNewUser() {
        userRepositoryImp.getUserforSA();
    }
}
