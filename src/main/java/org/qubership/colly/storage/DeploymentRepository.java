package org.qubership.colly.storage;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.qubership.colly.db.Deployment;

@ApplicationScoped
public class DeploymentRepository implements PanacheRepository<Deployment> {
}
