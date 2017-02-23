package com.gm.security;

import com.gm.api.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.gm.api.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Optional;

/**
 * Created by gregor on 12.1.2017.
 */
public class TimeTrackerAuthenticator implements Authenticator<BasicCredentials, User> {

    UserDAO userDAO;

    public TimeTrackerAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @UnitOfWork
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        User user = userDAO.findByUsername(credentials.getUsername());

        if (user.getPassword().equals(credentials.getPassword())) {
            System.out.println("Authenticated");
            return Optional.of(user);
        }
        System.out.println("Boo hooo:-P");
        return Optional.empty();
    }
}
