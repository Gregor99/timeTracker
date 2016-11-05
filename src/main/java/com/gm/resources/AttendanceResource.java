package com.gm.resources;

import com.gm.api.Attendance;
import com.gm.api.AttendanceDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.joda.time.*;
import java.util.List;

/**
 * Created by gregor on 26.10.2016.
 */

@Path("/track")
@Produces(MediaType.APPLICATION_JSON)
public class AttendanceResource {

    private final AttendanceDAO attendanceDAO;

    public AttendanceResource(AttendanceDAO dDAO) {
        this.attendanceDAO = dDAO;
    }

    @GET
    @UnitOfWork
    public List<Attendance> viewAll() {
        return attendanceDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("/{username}")
    public List<Attendance> today(@PathParam("username") Integer userName) {
        return attendanceDAO.findByUserAndDate(userName, new LocalDate());
    }
}

