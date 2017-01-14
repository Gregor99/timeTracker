package com.gm.api;

import java.security.Principal;

/**
 * Created by gregor on 12.1.2017.
 */
public class User implements Principal {



    private String username;

    public User(String username) {
        super();
        this.username = username;
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
