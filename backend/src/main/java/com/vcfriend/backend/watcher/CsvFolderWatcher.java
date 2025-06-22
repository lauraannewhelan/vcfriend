package com.vcfriend.backend.watcher;

import com.vcfriend.backend.service.VcfParserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@Component
@RequiredArgsConstructor
public class CsvFolderWatcher {

    private static final String FOLDER_TO_WATCH = "src/vcf_storage";

    private final VcfParserService parserService;

    @PostConstruct
    public void watchCsvFolder() {
        Thread watcherThread = new Thread(() -> {
            try {
                Path path = Paths.get(FOLDER_TO_WATCH);
                Files.createDirectories(path);  // ✅ Ensure directory exists

                WatchService watchService = FileSystems.getDefault().newWatchService();
                path.register(watchService, ENTRY_CREATE);

                System.out.println("👀 Watching folder for CSV uploads: " + path.toAbsolutePath());

                while (true) {
                    WatchKey key = watchService.take(); // wait for event
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        if (kind == ENTRY_CREATE) {
                            String filename = event.context().toString();
                            if (filename.endsWith(".csv")) {
                                String fullPath = path.resolve(filename).toString();
                                System.out.println("📄 New CSV detected: " + fullPath);
                                parserService.parseAndStoreCsv(fullPath);
                            }
                        }
                    }
                    key.reset(); // continue watching
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        watcherThread.setDaemon(true);
        watcherThread.start();
    }
}
