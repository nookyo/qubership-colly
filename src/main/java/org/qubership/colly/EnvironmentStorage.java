package org.qubership.colly;

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
        System.out.println("Executing task");
        environments = environmentsLoader.loadEnvironments();
        environments.forEach(System.out::println);
        System.out.println("Finished executing task");
    }

    public List<Environment> getEnvironments() {
        return Collections.unmodifiableList(environments);
    }
}
