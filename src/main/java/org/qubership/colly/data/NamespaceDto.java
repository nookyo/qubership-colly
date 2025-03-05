package org.qubership.colly.data;

import java.util.List;

public record NamespaceDto(String name,
                           String uid,
                           String envName,
                           List<DeploymentDto> deploymentDtos,
                           List<ConfigMapDto> configMapDtos,
                           List<Pod> pods) {
}
