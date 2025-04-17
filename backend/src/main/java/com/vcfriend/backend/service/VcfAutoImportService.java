package com.vcfriend.backend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class VcfAutoImportService {

    @Value("${vcf.upload.dir}")
    private String uploadDir;

    private final VcfService vcfService;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public VcfAutoImportService(VcfService vcfService) {
        this.vcfService = vcfService;
    }

    @PostConstruct
    public void startWatching() {
        executor.submit(() -> {
            try {
                Path path = Paths.get(uploadDir);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                    System.out.println("📁 Created missing upload folder: " + path.toAbsolutePath());
                }
                watchForNewFiles(path);
            } catch (Exception e) {
                System.err.println("❌ Auto-import service failed: " + e.getMessage());
            }
        });
    }

    private void watchForNewFiles(Path path) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        System.out.println("🔍 VCF Auto-Import Service is watching: " + path.toAbsolutePath());

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    String fileName = event.context().toString();
                    if (fileName.endsWith(".vcf")) {
                        File newVcf = path.resolve(fileName).toFile();
                        System.out.println("📥 New VCF file detected: " + newVcf.getName());

                        try {
                            vcfService.saveAndParseVcf(newVcf);
                            System.out.println("✅ VCF file imported successfully: " + newVcf.getName());
                        } catch (Exception e) {
                            System.err.println("❌ Failed to auto-import VCF: " + e.getMessage());
                        }
                    }
                }
            }
            key.reset();
        }
    }
}
