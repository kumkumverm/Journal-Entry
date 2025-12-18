package net.engineeringdigest.journalApp.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import net.engineeringdigest.journalApp.entity.User;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        return Stream.of(
            
                Arguments.of(User.builder().userName("Shayamm").password("Shhayamm").build()),
                Arguments.of(User.builder().userName("suraj").password("").build())
            
        );
    }
    
}
