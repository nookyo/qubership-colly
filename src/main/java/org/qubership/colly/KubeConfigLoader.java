package org.qubership.colly;

import io.kubernetes.client.util.KubeConfig;
import io.quarkus.logging.Log;
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
        Log.debug("Loading kubeconfigs from " + dir);
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
            Log.error("[ERROR] Failed to read files: " + e.getMessage());
        }
        Log.info("[INFO] Loaded " + kubeConfigs.size() + " kubeconfigs");
        return kubeConfigs;
    }
}
