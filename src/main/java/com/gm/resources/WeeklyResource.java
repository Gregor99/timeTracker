package com.gm.resources;

import com.gm.api.Attendance;
import com.gm.api.AttendanceDAO;
import com.gm.api.Weekly;
import com.gm.api.WeeklyDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.LocalDate;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by gregor on 28.11.2016.
 */

@Path("/weekly")
@Produces(MediaType.APPLICATION_JSON)
public class WeeklyResource {

    private final WeeklyDAO weeklyDAO;

    public WeeklyResource(WeeklyDAO dDAO) {
        this.weeklyDAO = dDAO;
    }

    @GET
    @UnitOfWork
    public List<Weekly> viewAll() {
        return weeklyDAO.findAll();
    }

    @GET
    @UnitOfWork
    @PermitAll
    @Path("/{username}")
    public Response firstDayOfWeek(@PathParam("username") Integer userName) {

//        List<Weekly> weekly = weeklyDAO.findByUserAndCurrentDate(userName);
        return Response.ok(weeklyDAO.findByUserAndCurrentDate(userName).get(0)).header("Access-Control-Allow-Origin", "http://localhost:63342").build();
    }

}
