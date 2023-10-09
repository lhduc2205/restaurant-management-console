package com.lhduc.util;

import com.lhduc.exception.ApplicationRuntimeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IdGenerator {
    private String fileName;

    public IdGenerator(String fileName) {
        this.fileName = fileName;
    }

    public int getGeneratedId() {
        int id = getCurrentIdInFile();
        updateId(id + 1);

        return id;
    }

    private int getCurrentIdInFile() {
        this.createFileIfNotExisted();

        try (InputStream inputStream = new FileInputStream(fileName)) {
            Scanner scanner = new Scanner(inputStream);
            int index = scanner.nextInt();

            return index < 0 ? 1 : index;
        } catch (FileNotFoundException e) {
            throw new ApplicationRuntimeException("File " + fileName + " was not found.");
        } catch (IOException e) {
            throw new ApplicationRuntimeException("Something went wrong while read file " + fileName);
        }
    }

    private void updateId(int newId) {
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.write(Integer.toString(newId).getBytes());
        } catch (FileNotFoundException e) {
            throw new ApplicationRuntimeException("File " + fileName + " was not found.");
        } catch (IOException e) {
            throw new ApplicationRuntimeException("Something went wrong while read file " + fileName);
        }
    }

    private void createFileIfNotExisted() {
        if(!Files.exists(Paths.get(fileName))) {
            FileUtil.ensureFileAndDirectoryExistence(fileName);
            updateId(1);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
