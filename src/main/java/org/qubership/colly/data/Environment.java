package org.qubership.colly.data;

import java.util.List;

public record Environment(String name, ClusterDto cluster, List<NamespaceDto> namespaceDtos) {
}
