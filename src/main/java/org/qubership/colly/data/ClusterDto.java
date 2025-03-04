package org.qubership.colly.data;

import java.util.List;

public record ClusterDto(String name, List<NamespaceDto> namespaceDtos) {
}
