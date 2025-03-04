package org.qubership.colly;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.qubership.colly.data.ClusterDto;
import org.qubership.colly.data.Environment;
import org.qubership.colly.db.Cluster;
import org.qubership.colly.storage.ClusterRepository;
import org.qubership.colly.storage.NamespaceRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CollyStorage {

    @Inject
    ClusterResourcesLoader clusterResourcesLoader;
    @Inject
    EnvironmentsLoader environmentsLoader;

    @Inject
    ClusterRepository clusterRepository;

    @Inject
    NamespaceRepository namespaceRepository;

    private List<Cluster> clusters = new ArrayList<>();

    @Scheduled(cron = "{cron.schedule}")
    @Transactional
    void executeTask() {
        Log.info("Task for loading resources from clusters has started");
        clusters = clusterResourcesLoader.loadClusters();
        for (Cluster cluster : clusters) {
            storeInDb(cluster);
        }
        Log.info("Task completed. Total clusters loaded: " + clusters.size());
    }

    public void storeInDb(Cluster newCluster) {
        Cluster cluster = clusterRepository.findByName(newCluster.name);

        if (cluster != null) {
            Log.info("before delete namespace count =" + namespaceRepository.count());
            clusterRepository.delete(cluster);
            Log.info("after delete namespace count =" + namespaceRepository.count());
        }
        Log.info("cluster is ready to persist " + newCluster.name);
        clusterRepository.persist(newCluster);
    }

    public List<Environment> getEnvironments() {
        return environmentsLoader.loadEnvironments();
    }

    public List<ClusterDto> getClusters() {
        return Collections.emptyList();
    }

    public List<Cluster> getClustersFromDb() {
        return clusterRepository.findAll().list();
    }
}
