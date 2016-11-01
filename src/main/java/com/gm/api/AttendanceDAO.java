package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.time.*;
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
            System.out.println("datum: " + date.toString());
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

//    public Holiday edit (Integer idHolidayOld, Holiday holidayNew) {
//        holidayNew.setIdUser(idHolidayOld);
//        currentSession().update(holidayNew);
//        return holidayNew;
//    }
}
