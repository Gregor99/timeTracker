package com.gm.resources;

import com.gm.api.*;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
    private final UserDAO userDAO;

    public AttendanceResource(AttendanceDAO aDAO, UserDAO uDAO) {

        this.attendanceDAO = aDAO;
        this.userDAO = uDAO;
    }

    @GET
    @UnitOfWork
    @Path("/all")
    public List<Attendance> viewAll() {
        return attendanceDAO.findAll();
    }

    @GET
    @UnitOfWork
    @PermitAll
    public Response today(@Context SecurityContext context) {
        //System.out.println("You are: " + user.getName());
        User userPrincipal = (User) context.getUserPrincipal();
        System.out.println("user Je: " + userPrincipal.getName());

        return Response.ok(attendanceDAO.findByUserAndDate(userPrincipal.getIdUser(), new LocalDate())).header("Access-Control-Allow-Origin", "http://localhost:63342").build();
    }

    //start or end work
    @POST
    @UnitOfWork
    @PermitAll
    public Response trackTime(@Context SecurityContext context) {
        User user = (User) context.getUserPrincipal();
        user = userDAO.findByUsername(user.getName());

        List<Attendance> todaysList = attendanceDAO.findByUserAndDate(user.getIdUser(), new LocalDate());

        Attendance todaysAttendance;
        if(todaysList.isEmpty()) {

            todaysAttendance = new Attendance(user.getIdUser(), new LocalDate(), new LocalDateTime());
            todaysList.add(attendanceDAO.saveToDataBase(todaysAttendance));
            return Response.ok(todaysList).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        } else if(todaysList.size() == 1 && todaysList.get(0).getTimeWorkEnd() == null) {

            todaysAttendance = todaysList.get(0);
            todaysAttendance.setTimeWorkEnd(new LocalDateTime());
            attendanceDAO.edit(todaysAttendance.getIdAttendance(), todaysAttendance);
            attendanceDAO.getWeeklyHours(user.getIdUser());
//            updateWeekly(todaysAttendance.getTimeWorkStart(), todaysAttendance.getTimeWorkEnd());
            return Response.ok(todaysList).header("Access-Control-Allow-Origin", "http://localhost:63342").build();

        }
        //TODO return wrong request al neki.
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "http://localhost:63342").build();
    }

    // cancel last start or end
    @PUT
    @UnitOfWork
    @PermitAll
    public Response cancelLastTime(@Context SecurityContext context) {
        User user = (User) context.getUserPrincipal();
        user = userDAO.findByUsername(user.getName());

        List<Attendance> todaysList = attendanceDAO.findByUserAndDate(user.getIdUser(), new LocalDate());
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

