package com.gm.resources;

import com.gm.api.Day;
import com.gm.api.DayDAO;
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

    private final DayDAO dayDAO;

    public DayResource(DayDAO dDAO) {
        this.dayDAO = dDAO;
    }

    @GET
    @UnitOfWork
    public List<Day> viewAll() {
        return dayDAO.findAll();
    }
}
