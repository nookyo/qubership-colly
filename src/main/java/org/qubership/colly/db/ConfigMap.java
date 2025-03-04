package org.qubership.colly.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ConfigMap extends PanacheEntityBase {
    @Id
    public String uid;
    public String name;
    //    public Map<String, String> content;
    @Lob
    public String configuration;

    public ConfigMap(String uid, String name, String configuration) {
        this.uid = uid;
        this.name = name;
        this.configuration = configuration;
    }

    public ConfigMap() {
    }
}
