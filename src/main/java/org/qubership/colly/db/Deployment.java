package org.qubership.colly.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Deployment extends PanacheEntityBase {
    @Id
    public String uid;
    public String name;
    public int replicas;
    @Lob
    public String configuration;

    public Deployment(String uid, String name, int replicas, String configuration) {
        this.uid = uid;
        this.name = name;
        this.replicas = replicas;
        this.configuration = configuration;
    }

    public Deployment() {
    }
}
