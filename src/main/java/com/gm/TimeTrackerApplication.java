package com.gm;

import com.gm.api.*;
import com.gm.resources.AttendanceResource;
import com.gm.resources.WeeklyResource;
import com.gm.security.TimeTrackerAuthenticator;
import com.gm.api.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TimeTrackerApplication extends Application<TimeTrackerConfiguration> {

    private final HibernateBundle<TimeTrackerConfiguration> hibernateBundle =
            new HibernateBundle<TimeTrackerConfiguration>(Attendance.class, User.class) {

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

        final AttendanceDAO attendanceDAO = new AttendanceDAO(hibernateBundle.getSessionFactory());
        final WeeklyDAO weeklyDAO = new WeeklyDAO(hibernateBundle.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new AttendanceResource(attendanceDAO));
        environment.jersey().register(new WeeklyResource(weeklyDAO));

        environment.jersey().register(
            new AuthDynamicFeature (
                new BasicCredentialAuthFilter.Builder<User>().setAuthenticator (
                    new UnitOfWorkAwareProxyFactory(hibernateBundle).create(TimeTrackerAuthenticator.class, UserDAO.class, userDAO)//TimeTrackerAuthenticator(userDAO)
                ).buildAuthFilter()
            )
        );
    }

}
