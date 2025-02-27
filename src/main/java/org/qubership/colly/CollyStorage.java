package org.qubership.colly;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.qubership.colly.data.ClusterDto;
import org.qubership.colly.data.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CollyStorage {

    @Inject
    ClusterResourcesLoader clusterResourcesLoader;
    @Inject
    EnvironmentsLoader environmentsLoader;

    private List<ClusterDto> clusters = new ArrayList<>();

    @Scheduled(cron = "{cron.schedule}")
    void executeTask() {
        Log.info("Task for loading resources from clusters has started");
        clusters = clusterResourcesLoader.loadClusters();
        Log.info("Task completed. Total clusters loaded: " + clusters.size());
    }

    public List<Environment> getEnvironments() {
        return environmentsLoader.loadEnvironments();
    }

    public List<ClusterDto> getClusters() {
        return Collections.unmodifiableList(clusters);
    }
}
