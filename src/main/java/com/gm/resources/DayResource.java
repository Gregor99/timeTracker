package com.gm.resources;

import com.gm.api.Attendance;
import com.gm.api.AttendanceDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by gregor on 26.10.2016.
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DayResource {

    private final AttendanceDAO attendanceDAO;

    public DayResource(AttendanceDAO dDAO) {
        this.attendanceDAO = dDAO;
    }

    @GET
    @UnitOfWork
    public List<Attendance> viewAll() {
        return attendanceDAO.findAll();
    }
}
