package com.gm.resources;

import com.gm.api.Attendance;
import com.gm.api.AttendanceDAO;
import com.gm.api.Weekly;
import com.gm.api.WeeklyDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.*;

import java.util.ArrayList;
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
    public Response today(@PathParam("username") Integer userName) {
        return Response.ok(attendanceDAO.findByUserAndDate(userName, new LocalDate())).header("Access-Control-Allow-Origin", "http://localhost:63342").build();
    }

    //start or end work
    @POST
    @UnitOfWork
    @Path("/{username}")
    public Response trackTime(@PathParam("username") Integer userName) {
        List<Attendance> todaysList = attendanceDAO.findByUserAndDate(userName, new LocalDate());

        Attendance todaysAttendance;
        if(todaysList.isEmpty()) {

            todaysAttendance = new Attendance(userName, new LocalDate(), new LocalDateTime());
            todaysList.add(attendanceDAO.saveToDataBase(todaysAttendance));
            return Response.ok(todaysList).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        } else if(todaysList.size() == 1 && todaysList.get(0).getTimeWorkEnd() == null) {

            todaysAttendance = todaysList.get(0);
            todaysAttendance.setTimeWorkEnd(new LocalDateTime());
            attendanceDAO.edit(todaysAttendance.getIdAttendance(), todaysAttendance);
            attendanceDAO.getWeeklyHours(userName);
//            updateWeekly(todaysAttendance.getTimeWorkStart(), todaysAttendance.getTimeWorkEnd());
            return Response.ok(todaysList).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        }
        //TODO return wrong request al neki.
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "http://localhost:63342").build();
    }

    // cancel last start or end
    @PUT
    @UnitOfWork
    @Path("/{username}")
    public Response cancelLastTime(@PathParam("username") Integer userName) {
        List<Attendance> todaysList = attendanceDAO.findByUserAndDate(userName, new LocalDate());
        Attendance todaysAttendance;
        if(todaysList.isEmpty()) {

            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        } else if(todaysList.size() == 1 && todaysList.get(0).getTimeWorkEnd() == null) {

            attendanceDAO.deleteFromDataBase(todaysList.get(0).getIdAttendance());
            return Response.ok().header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        } else if(todaysList.size() == 1 && todaysList.get(0).getTimeWorkEnd() != null) {

            todaysAttendance = todaysList.get(0);
            todaysAttendance.setTimeWorkEnd(null);
            attendanceDAO.edit(todaysAttendance.getIdAttendance(), todaysAttendance);

            return Response.ok(todaysList).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        }

        return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

    }

//    public void getWeeklyHours(Integer userName) {
//
//        List<Weekly> weeks = weeklyDAO.findByUserAndCurrentDate(userName);
//    }

}

