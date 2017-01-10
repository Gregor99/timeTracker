package com.gm.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by gregor on 18.11.2016.
 */

@Entity
@Table(name = "weekly_hours")
@NamedQueries({
        @NamedQuery(name = "findAll",
                query = "select a from weekly_hours a"),
        @NamedQuery(name = "findByUser",
                query = "select a from weekly_hours a where id_user = :id_user"),
        @NamedQuery(name = "findWeeklyByUserAndDate",
                query = "select a from weekly_hours a where id_user = :id_user and date_start = :date_start")
})
public class Weekly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_weekly_hours")
    private Integer idWeekly;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "sum_hours")
    private LocalDateTime sumHours;

    public Weekly() {}

    public Weekly(Integer idWeekly, Integer idUser, LocalDate dateStart, LocalDate dateEnd, LocalDateTime sumHours) {

        this.idWeekly = idWeekly;
        this.idUser = idUser;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.sumHours = sumHours;
    }

    @JsonProperty
    public Integer getIdWeekly() {
        return this.idWeekly;
    }

    @JsonProperty
    public void setIdWeekly(Integer idWeekly) {
        this.idWeekly = idWeekly;
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
    public LocalDate getDateStart() { return this.dateStart; }

    @JsonProperty
    public void setDateStart(LocalDate dateStart) { this.dateStart = dateStart; }

    @JsonProperty
    public LocalDate getDateEnd() { return this.dateEnd; }

    @JsonProperty
    public void setDateEnd(LocalDate dateEnd) { this.dateStart = dateEnd; }

    @JsonProperty
    public LocalDateTime getSumHours() { return sumHours; }

    @JsonProperty
    public void setSumHours(LocalDateTime sumHours) { this.sumHours = sumHours; }
}
