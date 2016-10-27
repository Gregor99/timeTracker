package com.gm.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by gregor on 26.10.2016.
 */

@Entity
@Table(name = "attendance")
@NamedQueries({
        @NamedQuery(name = "findAll",
                query = "select a from Attendance a"),

        @NamedQuery(name = "findByUser",
                query = "select a from Attendance a where id_user = :id_user")
})
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attendance")
    private Integer idDay;

    @Column(name = "id_user")
    private Integer idUser;


    public Attendance() {}

    public Attendance(Integer idDay) { this.idDay = idDay;  }

    public Attendance(Integer idDay, Integer idUser) {
        this.idDay = idDay;
        this.idUser = idUser;
    }


    @JsonProperty
    public Integer getIdDay() { return this.idDay; }

    @JsonProperty
    public void setIdDay(Integer idDay) {
        this.idDay = idDay;
    }

    @JsonProperty
    public Integer getIdUser() {
        return this.idUser;
    }

    @JsonProperty
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
