package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gregor on 17.1.2017.
 */
public class UserDAO extends AbstractDAO<User> {


    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Integer idUser) {
        return get(idUser);
    }

    public User findByUsername(String username) {
        User user = list(namedQuery("findByUserName").setParameter("username", username)).get(0);
        return user;
    }
}

