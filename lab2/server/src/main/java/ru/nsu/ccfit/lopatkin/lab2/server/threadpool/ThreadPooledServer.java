package ru.nsu.ccfit.lopatkin.lab2.server.threadpool;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.lab2.server.utils.ClientTasksContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServer implements Runnable{

    private static final Logger logger = Logger.getLogger(ThreadPooledServer.class);

    private static final String ERROR_ACCEPTING_CLIENT_CONNECTION = "Error accepting client connection";
    private static final String ERROR_CLOSING_SERVER = "Error closing server";
    private static final String CANNOT_OPEN_PORT = "Cannot open port ";

    private final int serverPort;
    protected ServerSocket serverSocket = null;

    protected boolean isStopped    = false;

    private final ExecutorService threadPool;

    private final ClientTasksContext clientTasksContext;

    public ThreadPooledServer(ClientTasksContext clientTasksContext, int port, int threadPoolThreadNumber) {
        this.clientTasksContext = clientTasksContext;
        this.serverPort = port;
        this.threadPool = Executors.newFixedThreadPool(threadPoolThreadNumber);
    }

    @Override
    public void run(){
        logger.info("Server started");
        this.openServerSocket();
        logger.info("Socket opened");
        while(!this.isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                logger.info("New client accepted: " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                if(this.isStopped()) {
                    break;
                }
                logger.fatal("Server socket exception\n" + e.getMessage());
                throw new RuntimeException(
                        ERROR_ACCEPTING_CLIENT_CONNECTION, e);
            }
            try {
                ClientTask clientTask = clientTask = new ClientTask(clientSocket, threadPool);
                this.clientTasksContext.addClientTask(clientTask);
                this.threadPool.execute(clientTask);
                logger.info("New task created");
            } catch (IOException e) {
                logger.error("Server new client accepting exception\n" + e.getMessage());
            }
        }
        this.threadPool.shutdown();
        logger.info("Server shutdown");
    }


    private boolean isStopped() {
        return this.isStopped;
    }

    public void stop(){
        this.isStopped = true;
        logger.info("Server stopped");
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_CLOSING_SERVER, e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException(CANNOT_OPEN_PORT + this.serverPort, e);
        }
    }
}
