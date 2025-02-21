package org.qubership.colly.data;

import java.util.List;

public record Environment(String name, Cluster cluster, List<Namespace> namespaces) {
}
