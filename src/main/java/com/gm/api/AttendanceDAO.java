package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

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
            return list(namedQuery("findByUser").setParameter("idUser", idUser));
        }

        public Attendance findById(Integer idDay) {
            return get(idDay);
        }

        public Attendance saveToDataBase(Attendance Attendance) {
            currentSession().save(Attendance);
            return Attendance;
        }

        public void deleteFromDataBase(Integer idDay) {
            currentSession().delete(new Attendance(idDay));
        }

//    public Holiday edit (Integer idHolidayOld, Holiday holidayNew) {
//        holidayNew.setIdUser(idHolidayOld);
//        currentSession().update(holidayNew);
//        return holidayNew;
//    }
}
