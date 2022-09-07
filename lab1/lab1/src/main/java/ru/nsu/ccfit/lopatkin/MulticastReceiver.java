package ru.nsu.ccfit.lopatkin;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];

    public void run() {
        try {
            System.out.println("Receiver starts");
            socket = new MulticastSocket(4446);
            InetAddress group = null;
            group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting messages.......");
                socket.receive(packet);
                String received = new String(
                        packet.getData(), 0, packet.getLength());
                System.out.println("Received: - " + received);
                if ("end".equals(received)) {
                    break;
                }
            }
            socket.leaveGroup(group);
            socket.close();
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
