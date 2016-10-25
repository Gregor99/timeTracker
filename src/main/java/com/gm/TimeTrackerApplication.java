package com.gm;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TimeTrackerApplication extends Application<TimeTrackerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TimeTrackerApplication().run(args);
    }

    @Override
    public String getName() {
        return "timeTracker";
    }

    @Override
    public void initialize(final Bootstrap<TimeTrackerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TimeTrackerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
