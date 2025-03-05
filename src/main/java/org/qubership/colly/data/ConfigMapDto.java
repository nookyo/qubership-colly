package org.qubership.colly.data;

import java.util.Map;

public record ConfigMapDto(String name, Map<String, String> content, String configuration) {
}
