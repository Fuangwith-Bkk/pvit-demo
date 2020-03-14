package com.example.freelancer.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class ApplicationLifeCycle {
    private static final Logger logger = Logger.getLogger(ApplicationLifeCycle.class);
    void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting with profile " + ProfileManager.getActiveProfile());
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The application is stopping...");
    }
}