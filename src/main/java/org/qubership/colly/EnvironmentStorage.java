package org.qubership.colly;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.qubership.colly.data.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvironmentStorage {

    @Inject
    EnvironmentsLoader environmentsLoader;

    private List<Environment> environments = new ArrayList<>();

    @Scheduled(cron = "{cron.schedule}")
    void executeTask() {
        Log.info("Task for loading resources from clusters has started");
        environments = environmentsLoader.loadEnvironments();
        Log.info("Task completed. Total environments loaded: " + environments.size());
    }

    public List<Environment> getEnvironments() {
        return Collections.unmodifiableList(environments);
    }
}
