package com.example.cleanupprocessfile.service;

import com.example.cleanupprocessfile.constants.FileExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CleanUpService {

    private final static Logger LOG = LoggerFactory.getLogger(CleanUpService.class);

    @Scheduled(fixedRate = 10000)
    public void performCleanUp() {
        File directory = new File("F:/AWS/files");
        if (directory.exists() && directory.isDirectory()) {
            List<File> files = Arrays.stream(Objects.requireNonNull(directory.listFiles())).toList();
            for (File file : files) {
                if (!hasExtension(file, FileExtension.TXT.label) && isLargerThan(file, 5 * 1024L)
                        && isOlderThan(file, Duration.ofMinutes(5L))) {
                    file.delete();
                    LOG.info(String.format("Deleted file: %s", file.getAbsolutePath()));
                }
            }
        }
    }

    private boolean hasExtension(File file, String fileExtension) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(fileExtension);
    }

    private boolean isLargerThan(File file, long sizeInBytes) {
        return file.length() > sizeInBytes;
    }

    private boolean isOlderThan(File file, Duration duration) {
        Long lastModifiedTime = file.lastModified();
        Instant lastModifiedInstant = Instant.ofEpochMilli(lastModifiedTime);
        Instant oldInstant = Instant.now().minus(duration);
        return lastModifiedInstant.isBefore(oldInstant);
    }
}
