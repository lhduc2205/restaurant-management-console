package com.lhduc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static File createDirectoryAndFileIfNotExist(String fileName) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return path.toFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return path.toFile();
    }
}
