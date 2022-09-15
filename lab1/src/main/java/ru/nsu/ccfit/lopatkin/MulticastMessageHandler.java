package ru.nsu.ccfit.lopatkin;

import java.net.Inet4Address;
import java.net.Inet6Address;

import java.net.UnknownHostException;

public class MulticastMessageHandler {

    private Context context;

    public MulticastMessageHandler(Context context) {
        this.context = context;
    }

    public void handleMessage(MulticastMessage multicastMessage) {
        context.updateHostState(multicastMessage.getHost());
        System.out.println("Handled message: " + multicastMessage.getText() +
                "\n From host: " + multicastMessage.getHost() +
                "\n Port: " + multicastMessage.getPort());
    }
}
