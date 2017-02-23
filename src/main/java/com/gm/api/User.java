package com.gm.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.Principal;

/**
 * Created by gregor on 12.1.2017.
 */

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findByUserName",
                query = "select a from User a where username = :username")
})
public class User implements Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password")
    private String password;


    public User() { super(); }

    public User(String username) {
        super();
        this.username = username;
    }

    public User(Integer id, String username, String password, String salt) {
        super();
        this.idUser = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @JsonProperty
    public Integer getIdUser() {
        return this.idUser;
    }

    @JsonProperty
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @JsonProperty
    public String getName() {
        return this.username;
    }

//    @JsonProperty
//    public void setName(String username) {
//        this.username = username;
//    }

    @JsonProperty
    public String getPassword() {
        return this.password;
    }
}
