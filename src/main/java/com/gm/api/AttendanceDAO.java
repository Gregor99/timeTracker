package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import org.joda.time.*;
import java.util.List;

/**
 * Created by gregor on 26.10.2016.
 */
public class AttendanceDAO extends AbstractDAO<Attendance> {

        public AttendanceDAO(SessionFactory sessionFactory) {
            super(sessionFactory);
        }

        public List<Attendance> findAll() {
            return list(namedQuery("findAll"));
        }

        public List<Attendance> findByUser(Integer idUser) {
            return list(namedQuery("findByUser").setParameter("id_user", idUser));
        }

        public List<Attendance> findByUserAndDate(Integer idUser, LocalDate date) {
            return list(namedQuery("findByUserAndDate").setParameter("id_user", idUser).setParameter("date", date));
        }

        public Attendance findById(Integer idAttendance) {
            return get(idAttendance);
        }

        public Attendance saveToDataBase(Attendance Attendance) {
            currentSession().save(Attendance);
            return Attendance;
        }

        public void deleteFromDataBase(Integer idAttendance) {
            currentSession().delete(new Attendance(idAttendance));
        }

    public Attendance edit (Integer idAttendanceOld, Attendance AttendanceNew) {
        AttendanceNew.setIdAttendance(idAttendanceOld);
        currentSession().update(AttendanceNew);
        return AttendanceNew;
    }

    public void getWeeklyHours(Integer idUser) {
        Period sum = new Period();
        LocalDate date = new LocalDate().withDayOfWeek(1);
        List<Attendance> week =  list(namedQuery("findCurrentWeek").setParameter("id_user", idUser).setParameter("date", date));
        for (Attendance day : week) {
            Period timeToday = new Period(day.getTimeWorkStart(), day.getTimeWorkEnd());
            sum.plus(timeToday);
        }

        System.out.println("vsota ur: " + sum.toString());
    }
}
