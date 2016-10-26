package com.gm.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gregor on 26.10.2016.
 */
public class DayDAO extends AbstractDAO<Day> {

        public DayDAO(SessionFactory sessionFactory) {
            super(sessionFactory);
        }

        public List<Day> findAll() {
            return list(namedQuery("findAll"));
        }

        public List<Day> findByUser(Integer idUser) {
            return list(namedQuery("findByUser").setParameter("idUser", idUser));
        }

        public Day findById(Integer idDay) {
            return get(idDay);
        }

        public Day saveToDataBase(Day Day) {
            currentSession().save(Day);
            return Day;
        }

        public void deleteFromDataBase(Integer idDay) {
            currentSession().delete(new Day(idDay));
        }

//    public Holiday edit (Integer idHolidayOld, Holiday holidayNew) {
//        holidayNew.setIdUser(idHolidayOld);
//        currentSession().update(holidayNew);
//        return holidayNew;
//    }
}
