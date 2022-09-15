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
        try {
            if (multicastMessage.getHost().equals(Inet4Address.getLocalHost().getHostAddress()) || multicastMessage.getHost().equals(Inet6Address.getLocalHost().getHostAddress())) {
                //System.out.println("I see myself!");
                return;
            }

        } catch (UnknownHostException e) {
            System.out.println("Cant get my own ip :(");
        }
        context.updateHostState(multicastMessage.getHost());
        System.out.println("Handled message: " + multicastMessage.getText() +
                "\n From host: " + multicastMessage.getHost() +
                "\n Port: " + multicastMessage.getPort());
    }
}
