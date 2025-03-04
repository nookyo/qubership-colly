package org.qubership.colly.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Pod extends PanacheEntityBase {

    @Id
    public String uid;
    public String name;
    public String status;
    @Lob
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
