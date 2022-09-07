package ru.nsu.ccfit.lopatkin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MulticastReceiver multicastReceiver = new MulticastReceiver();
        multicastReceiver.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MulticastPublisher multicastPublisher = new MulticastPublisher();
        try {
            multicastPublisher.multicast("Hi");
            multicastPublisher.multicast("asd");
            multicastPublisher.multicast("qwe");

            multicastPublisher.multicast("end");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            multicastReceiver.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}