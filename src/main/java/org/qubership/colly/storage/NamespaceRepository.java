package org.qubership.colly.storage;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.qubership.colly.db.Namespace;

@ApplicationScoped
public class NamespaceRepository implements PanacheRepository<Namespace> {
}
