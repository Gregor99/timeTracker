package com.gm.resources;

import com.gm.api.Attendance;
import com.gm.api.AttendanceDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.jboss.logging.annotations.Param;
import org.joda.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by gregor on 26.10.2016.
 */

@Path("/")
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
    public List<Attendance> today(@PathParam(value = "username") String userName) {
        return attendanceDAO.findByUserAndDate(Integer.valueOf(userName), new LocalDate());
    }
}

