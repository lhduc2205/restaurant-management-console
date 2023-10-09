package com.lhduc.util;

import com.lhduc.exception.ApplicationRuntimeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file operations.
 */
public class FileUtil {

    /**
     * Ensures that a file and its parent directory exist at the specified path. If they do not
     * exist, it creates the directory and an empty file.
     *
     * @param fileName The path to the file.
     * @return A File object representing the created or existing file.
     * @throws ApplicationRuntimeException If it can not create Directories or create File.
     */
    public static File ensureFileAndDirectoryExistence(String fileName) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            try {
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }

                Files.createFile(path);
                return path.toFile();
            } catch (IOException e) {
                throw new ApplicationRuntimeException();
            }
        }

        return path.toFile();
    }
}
