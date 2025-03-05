package org.qubership.colly.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "pods")
public class Pod extends PanacheEntityBase {

    @Id
    public String uid;
    public String name;
    public String status;
    @Column(columnDefinition = "TEXT")
    public String configuration;

    public Pod(String uid, String name, String status, String configuration) {
        this.uid = uid;
        this.name = name;
        this.status = status;
        this.configuration = configuration;
    }

    public Pod() {
    }
}
