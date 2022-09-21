package ru.nsu.ccfit.lopatkin.lab2.server.threadpool;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.lab2.protocol.ClientRequest;
import ru.nsu.ccfit.lopatkin.lab2.protocol.ResponseCode;
import ru.nsu.ccfit.lopatkin.lab2.protocol.ServerResponse;
import ru.nsu.ccfit.lopatkin.lab2.protocol.exceptions.BadResponse;
import ru.nsu.ccfit.lopatkin.lab2.server.exceptions.FileCreateException;
import ru.nsu.ccfit.lopatkin.lab2.server.utils.FileContext;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ClientTask implements Runnable{

    private static final Logger logger = Logger.getLogger(ClientTask.class);

    private static final long SPEED_TEST_INTERVAL = 3000;
    private final Socket clientSocket;

    private FileContext fileContext;

    private boolean isFileExchangeStarts = false;

    private int bufferSize;

    private long currentSpeed = -1;

    private final ExecutorService threadPool;

    private long prevAllReadBytes = 0;

    private final long initTime;
    private long lastTime;

    private boolean isStopped = false;

    private final DataInputStream clientDataReader;
    private final ObjectInputStream clientObjectReader;
    private final ObjectOutputStream clientObjectWriter;

    public ClientTask(Socket clientSocket, ExecutorService threadPool) throws IOException {
        this.clientSocket = clientSocket;
        this.threadPool = threadPool;
        this.initTime = System.currentTimeMillis();
        this.lastTime = this.initTime;

        this.clientDataReader = new DataInputStream(this.clientSocket.getInputStream());
        this.clientObjectReader = new ObjectInputStream(this.clientSocket.getInputStream());
        this.clientObjectWriter = new ObjectOutputStream(this.clientSocket.getOutputStream());

    }

    private void doCleanUp() throws IOException {
        this.clientDataReader.close();
        this.clientObjectReader.close();
        this.clientObjectWriter.close();
        this.fileContext.close();
        this.clientSocket.close();
    }

    public void run() {
        try {
            if (!this.isFileExchangeStarts) {
                ClientRequest clientRequest = (ClientRequest) clientObjectReader.readObject();
                try {
                    this.prepareFileExchange(clientRequest);
                } catch (FileCreateException e) {
                    clientObjectWriter.writeObject(new ServerResponse(ResponseCode.FAILURE_FILENAME_TRANSFER, new BadResponse("Something goes wrong :( \n" + e.getMessage())));
                    logger.error("Task error while file accepting " + e.getMessage());
                    this.doCleanUp();
                    return;
                }
                clientObjectWriter.writeObject(new ServerResponse(ResponseCode.SUCCESS_FILENAME_TRANSFER));
                logger.info("Task with file - {" + this.fileContext.getFileName() +"} accepted in work");
            }

            try {
                byte[] buffer = new byte[this.bufferSize];
                if (clientDataReader.available() > 0) {
                    int readBytes = clientDataReader.read(buffer, 0, this.bufferSize);
                    this.fileContext.addDataToFile(buffer, readBytes);
                }
            } catch (IOException e) {
                clientObjectWriter.writeObject(new ServerResponse(ResponseCode.FAILURE_FILE_TRANSFER, new BadResponse("Something goes wrong :( \n" + e.getMessage())));
                logger.error("Task with file - {" + this.fileContext.getFileName() + "} error while file reading " + e.getMessage());
                this.doCleanUp();
                return;
            }
            this.printStatistic();

            if(this.fileContext.IsFileDownloadSuccessfully()) {
                long speed = this.fileContext.getAllReadBytes() * 1000 / (System.currentTimeMillis() - this.lastTime);
                clientObjectWriter.writeObject(new ServerResponse(ResponseCode.SUCCESS_FILE_TRANSFER));
                logger.info("Task with file - {" + this.fileContext.getFileName() + "} finished! avg speed = " + speed + " byte/sec");
                this.doCleanUp();
                return;
            }

            if (!this.isStopped) {
                this.threadPool.execute(this);
            }
            else {
                clientObjectWriter.writeObject(new ServerResponse(ResponseCode.FAILURE_FILE_TRANSFER, new BadResponse("Something goes wrong :( We stopped file transferring")));
                this.doCleanUp();
                logger.error("Task with file - {" + this.fileContext.getFileName() + "} stopped!");
            }
        } catch (Exception e) {
            logger.error("Task socket ex - " + e.getMessage());
            try {
                this.doCleanUp();
            } catch (IOException ignored) {}
        }
    }

    private void printStatistic() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastTime > SPEED_TEST_INTERVAL){
            this.currentSpeed = (this.fileContext.getAllReadBytes() - this.prevAllReadBytes) * 1000 / (currentTime - this.lastTime);
            long avgSpeed = this.fileContext.getAllReadBytes() * 1000 / (currentTime - this.initTime);
            logger.info("Task with file - {" + this.fileContext.getFileName() + "} has current speed = " + this.currentSpeed + " byte/sec" + ", avg speed = " + avgSpeed + " byte/sec");
            this.lastTime = currentTime;
            this.prevAllReadBytes = this.fileContext.getAllReadBytes();
        }
    }

    public void stopTask() {
        this.isStopped = true;
        logger.info("Task with file - {" + this.fileContext.getFileName() +"} stopped");
    }

    public boolean checkIsTaskAlive() {
        return !(this.currentSpeed == 0 || this.fileContext.IsFileDownloadSuccessfully()); // жестко упростил благодаря 1.5 годам матлога B)
    }
    private void prepareFileExchange(ClientRequest clientRequest) throws FileCreateException {
        this.fileContext = new FileContext(clientRequest.getFileName(), clientRequest.getFileSize());
        this.bufferSize = clientRequest.getBufferSize();
        this.isFileExchangeStarts = true;
    }
}
