package com.gm;

import com.gm.api.Day;
import com.gm.api.DayDAO;
import com.gm.resources.DayResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TimeTrackerApplication extends Application<TimeTrackerConfiguration> {

    private final HibernateBundle<TimeTrackerConfiguration> hibernateBundle =
            new HibernateBundle<TimeTrackerConfiguration>(Day.class) {

                @Override
                public PooledDataSourceFactory getDataSourceFactory(TimeTrackerConfiguration timeTrackerConfiguration) {
                    return timeTrackerConfiguration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new TimeTrackerApplication().run(args);
    }

    @Override
    public String getName() {
        return "timeTracker";
    }

    @Override
    public void initialize(final Bootstrap<TimeTrackerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final TimeTrackerConfiguration configuration,
                    final Environment environment) {

        final DayDAO dayDAO = new DayDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new DayResource(dayDAO));
    }

}
