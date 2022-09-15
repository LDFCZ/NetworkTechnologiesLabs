package ru.nsu.ccfit.lopatkin;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.*;


public class MulticastReceiver implements Runnable {
    private MulticastSocket socket ;

    private InetSocketAddress group;

    private NetworkInterface networkInterface;
    private String host;

    private int port;

    private MulticastMessageHandler multicastMessageHandler;

    private boolean isRunning = true;

    public MulticastReceiver(@NotNull String host,int port, @NotNull String networkInterfaceName, MulticastMessageHandler multicastMessageHandler) throws IOException {
        this.host = host;
        this.port = port;
        this.multicastMessageHandler = multicastMessageHandler;

        InetAddress multicastAddress = InetAddress.getByName(host);
        group = new InetSocketAddress(multicastAddress, port);
        networkInterface = NetworkInterface.getByName(networkInterfaceName);
        socket = new MulticastSocket(port);
        socket.joinGroup(new InetSocketAddress(multicastAddress, 0), networkInterface);
    }

    @Override
    public void run() {
        try {
            System.out.println("Receiver starts");

            while (isRunning) {
                byte[] buf = new byte[1000];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);
                String received = new String(
                        packet.getData(), 0, packet.getLength());
                multicastMessageHandler.handleMessage(new MulticastMessage(packet.getAddress().getHostAddress(), packet.getPort(), received));
            }
            socket.leaveGroup(group, networkInterface);
            socket.close();

        }  catch (Exception e) {
            System.out.println("Something goes wrong while receiving multicast messages");
        }
    }

    public void stop() {
        String multicastMessage = "end me";
        byte[] buf = multicastMessage.getBytes();
        try {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length, InetAddress.getByName(this.host), this.port);
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.isRunning = false;
    }
}
