package ru.nsu.ccfit.lopatkin.lab2.client;

import ru.nsu.ccfit.lopatkin.lab2.protocol.ClientRequest;
import ru.nsu.ccfit.lopatkin.lab2.protocol.ResponseCode;
import ru.nsu.ccfit.lopatkin.lab2.protocol.ServerResponse;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClientRoutine {

    private final Path filePath;

    private final String host;

    private final int port;

    private static final int BUFFER_SIZE = 1000;

    public ClientRoutine(Path filePath, String host, int port) {
        this.filePath = filePath;
        this.host = host;
        this.port = port;
    }

    public void startRoutine() {
        System.out.println(this.host);
        try (Socket socket = new Socket(this.host, this.port);
             InputStream fileReader = Files.newInputStream(this.filePath);
             DataOutputStream socketDataWriter = new DataOutputStream(socket.getOutputStream());
             ObjectOutputStream socketObjectWriter = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream socketObjectReader = new ObjectInputStream(socket.getInputStream())) {
            System.out.println(socket.getInetAddress().getHostAddress());
            long fileSize = Files.size(this.filePath);
            String fileName = this.filePath.getFileName().toString();

            ClientRequest clientRequest = new ClientRequest(fileName, fileSize, BUFFER_SIZE);
            socketObjectWriter.writeObject(clientRequest);
            socketObjectWriter.flush();
            ServerResponse filenameTransferResponse = (ServerResponse)socketObjectReader.readObject();
            if (filenameTransferResponse.getResponseCode() == ResponseCode.FAILURE_FILENAME_TRANSFER){
                if (filenameTransferResponse.getResponseException().isPresent())
                    System.out.println("Failure file name transfer" + filenameTransferResponse.getResponseException().get().getMessage());
                return;
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            int lineSize;
            while ((lineSize = fileReader.read(buffer, 0, BUFFER_SIZE)) > 0){
                socketDataWriter.write(buffer, 0, lineSize);
                socketDataWriter.flush();
            }
            ServerResponse fileTransferResponse = (ServerResponse)socketObjectReader.readObject();
            if (fileTransferResponse.getResponseCode() == ResponseCode.FAILURE_FILE_TRANSFER){
                System.out.println("Failure file transfer");
            }

        } catch (UnknownHostException e) {
            System.out.println("Socket broken...... No such host " + this.host);
        } catch (IOException e) {
            System.out.println("Socket broken......" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
