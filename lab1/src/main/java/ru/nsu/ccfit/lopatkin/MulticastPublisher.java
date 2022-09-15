package ru.nsu.ccfit.lopatkin;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.*;

public class MulticastPublisher implements Runnable{

    private String host;
    private int port;
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;

    private boolean isRunning = true;

    public MulticastPublisher(@NotNull String host, int port) throws SocketException, UnknownHostException {
        this.host = host;
        this.port = port;

        socket = new DatagramSocket();
        group = InetAddress.getByName(host);
    }

    @Override
    public void run() {
        try {
            System.out.println("Publisher starts");
            String multicastMessage = "test message";
            while (isRunning) {
                buf = multicastMessage.getBytes();

                DatagramPacket packet
                        = new DatagramPacket(buf, buf.length, this.group, this.port);

                socket.send(packet);
                Thread.sleep(1000);
            }
            socket.close();

        } catch (IOException e) {
            System.out.println("Something goes wrong while sending multicast message");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
