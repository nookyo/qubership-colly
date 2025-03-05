package org.qubership.colly.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Map;

@Entity(name = "configmaps")
public class ConfigMap extends PanacheEntityBase {
    @Id
    public String uid;
    public String name;

    @ElementCollection
    @CollectionTable(name = "config_map_content", joinColumns = @JoinColumn(name = "uid"))
    @MapKeyColumn(name = "map_key")
    @Column(name = "map_value", columnDefinition = "TEXT")
    public Map<String, String> content;
    @Column(columnDefinition = "TEXT")
    public String configuration;

    public ConfigMap(String uid, String name, Map<String, String> content, String configuration) {
        this.uid = uid;
        this.name = name;
        this.content = content;
        this.configuration = configuration;
    }

    public ConfigMap() {
    }
}
