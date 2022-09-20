package ru.nsu.ccfit.lopatkin.lab2.protocol;

import java.io.Serializable;

public class ClientRequest implements Serializable {

    private final String fileName;

    private final long fileSize;

    private final long bufferSize;

    public ClientRequest(String fileName, long fileSize) {
        this(fileName, fileSize, 1000);
    }

    public ClientRequest(String fileName, long fileSize, long bufferSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.bufferSize = bufferSize;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getBufferSize() {
        return bufferSize;
    }
}
