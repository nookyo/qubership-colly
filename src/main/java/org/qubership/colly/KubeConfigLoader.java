package org.qubership.colly;

import io.kubernetes.client.util.KubeConfig;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class KubeConfigLoader {

    @ConfigProperty(name = "kubeconfigs.path")
    String kubeconfigFolder;

    public List<KubeConfig> loadKubeConfigs() {
        List<KubeConfig> kubeConfigs = new ArrayList<>();
        Path dir = Paths.get(kubeconfigFolder);
        try (Stream<Path> paths = Files.list(dir)) {
            paths.filter(Files::isRegularFile)
                    .forEach(fileName -> {
                        try {
                            kubeConfigs.add(KubeConfig.loadKubeConfig(Files.newBufferedReader(fileName)));
                        } catch (IOException e) {
                            throw new RuntimeException("[ERROR] unable to read file - " + fileName, e);
                        }
                    });
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to read files: " + e.getMessage());
        }

        System.out.println(kubeConfigs.size() + " kubeconfigs parsed");
        return kubeConfigs;
    }
}
