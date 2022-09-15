package ru.nsu.ccfit.lopatkin;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Context context = new Context();

        String host = "230.0.0.0";
        int port = 4446;

        MulticastMessageHandler multicastMessageHandler = new MulticastMessageHandler(context);

        // Set dead host checker
        CheckDeadHostsTask checkDeadHostsTask = new CheckDeadHostsTask(context);
        Timer timer = new Timer();
        timer.schedule(checkDeadHostsTask, 1000, 3000);

        MulticastPublisher multicastPublisher = null;
        MulticastReceiver multicastReceiver = null;

        try {
            multicastPublisher = new MulticastPublisher(host, port);
            multicastReceiver = new MulticastReceiver(host, port, "em0", multicastMessageHandler);

        } catch (IOException e) {
            System.out.println("Thread creating trouble!");
        }

        Thread publisher = new Thread(multicastPublisher);
        Thread receiver = new Thread(multicastReceiver);


        receiver.start();
        publisher.start();

        while (true) {
            Scanner in = new Scanner(System.in);

            if(in.nextLine().equals("e")) break;
        }
        multicastPublisher.stop();
        multicastReceiver.stop();
        timer.cancel();
        try {
            publisher.join();
            receiver.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}