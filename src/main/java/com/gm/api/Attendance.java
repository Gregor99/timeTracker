package com.gm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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
                query = "select a from Attendance a where id_user = :id_user"),
        @NamedQuery(name = "findByUser&Date",
                query = "select a from Attendance a where id_user = :id_user and date = :date")
})
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attendance")
    private Integer idAttendance;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time_work_start")
    private DateTime timeWorkStart;

//    @Column(name = "time_lunch_start")
//    private DateTime timeLunchStart;
//
//    @Column(name = "time_lunch_end")
//    private DateTime timeLunchEnd;

    @Column(name = "time_work_end")
    private DateTime timeWorkEnd;


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

    @JsonProperty
    public LocalDate getDate() { return date; }

    @JsonProperty
    public void setDate(LocalDate date) { this.date = date; }

    @JsonProperty
    public DateTime getTimeWorkStart() {
        return this.timeWorkStart;
    }

    @JsonProperty
    public void setTimeWorkStart(DateTime timeWorkStart) {
        this.timeWorkStart = timeWorkStart;
    }

    @JsonProperty
    public DateTime getTimeWorkEnd() {
        return this.timeWorkEnd;
    }

    @JsonProperty
    public void setTimeWorkEnd(DateTime timeWorkEnd) {
        this.timeWorkEnd = timeWorkEnd;
    }

}
