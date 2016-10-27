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
    private Integer idAttendance;

    @Column(name = "id_user")
    private Integer idUser;


    public Attendance() {}

    public Attendance(Integer idAttendance) { this.idAttendance = idAttendance;  }

    public Attendance(Integer idAttendance, Integer idUser) {
        this.idAttendance = idAttendance;
        this.idUser = idUser;
    }


    @JsonProperty
    public Integer getIdAttendance() { return this.idAttendance; }

    @JsonProperty
    public void setIdAttendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
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
