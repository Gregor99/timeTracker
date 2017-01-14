package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by gregor on 18.11.2016.
 */
public class WeeklyDAO extends AbstractDAO<Weekly> {

public WeeklyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        }

public List<Weekly> findAll() {
        return list(namedQuery("findAll"));
        }

public List<Weekly> findByUser(Integer idUser) {
        return list(namedQuery("findByUser").setParameter("id_user", idUser));
        }

public List<Weekly> findByUserAndDate(Integer idUser, LocalDate date) {
        return list(namedQuery("findByUserAndDate").setParameter("id_user", idUser).setParameter("date_start", date));
        }

    public List<Weekly> findByUserAndCurrentDate(Integer idUser) {
        LocalDate date = new LocalDate().withDayOfWeek(1);
        System.out.println("prvi dan tega tedna: " + date.toString());
        return list(namedQuery("findWeeklyByUserAndDate").setParameter("id_user", idUser).setParameter("date_start", date.toString()));
        }

public Weekly findById(Integer idAttendance) {
        return get(idAttendance);
        }

public Weekly saveToDataBase(Weekly weekly) {
        currentSession().save(weekly);
        return weekly;
        }

public void deleteFromDataBase(Integer idAttendance) {
        currentSession().delete(new Attendance(idAttendance));
        }

public Attendance edit (Integer idAttendanceOld, Attendance AttendanceNew) {
        AttendanceNew.setIdAttendance(idAttendanceOld);
        currentSession().update(AttendanceNew);
        return AttendanceNew;
        }
}
