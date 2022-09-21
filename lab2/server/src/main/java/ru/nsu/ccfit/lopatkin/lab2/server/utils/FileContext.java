package ru.nsu.ccfit.lopatkin.lab2.server.utils;

import ru.nsu.ccfit.lopatkin.lab2.server.exceptions.FileCreateException;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileContext implements Closeable {

    static volatile long fileCounter = 0;
    private final String fileName;
    private final long fileSize;
    private long allReadBytes = 0;

    private final OutputStream fileWriter;

    public FileContext(String fileName, long fileSize) throws FileCreateException {
        this.fileName = fileName;
        this.fileSize = fileSize;

        try {
            this.fileWriter = Files.newOutputStream(createFile());
        } catch (IOException e) {
            throw new FileCreateException("Failed while creating file" + e.getMessage());
        }
    }

    public void addDataToFile(byte[] buffer, int readBytes) throws IOException {
        this.fileWriter.write(buffer, 0, readBytes);
        this.allReadBytes += readBytes;
    }

    private void closeFile() throws IOException {
        this.fileWriter.close();
    }

    public boolean IsFileDownloadSuccessfully() {
        return this.allReadBytes == this.fileSize;
    }

    public long getAllReadBytes() {
        return this.allReadBytes;
    }

    public String getFileName() {
        return this.fileName;
    }

    private Path createFile() throws IOException {
        Path dirPath = Paths.get("uploads");
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        Path path = Paths.get(dirPath + System.getProperty("file.separator") + this.fileName);
        if (Files.exists(path)){
            path = Paths.get(dirPath + System.getProperty("file.separator") + generateRandomFileName(this.fileName));
        }
        Files.createFile(path);
        return path;
    }

    private String generateRandomFileName(String fileName){
        return fileCounter + "_" + fileName;
    }

    @Override
    public void close() throws IOException {
        closeFile();
    }
}
