package org.qubership.colly.storage;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.qubership.colly.db.Cluster;

@ApplicationScoped
public class ClusterRepository implements PanacheRepository<Cluster> {

    public Cluster findByName(String name){
        return find("name", name).firstResult();
    }
}
