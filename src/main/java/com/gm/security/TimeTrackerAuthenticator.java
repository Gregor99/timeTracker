package com.gm.security;

import com.gm.api.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

/**
 * Created by gregor on 12.1.2017.
 */
public class TimeTrackerAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("secret".equals(credentials.getPassword())) {
            System.out.println("Authenticated");
            return Optional.of(new User(credentials.getUsername()));
        }
        System.out.println("Boo hooo:-P");
        return Optional.empty();
    }
}
