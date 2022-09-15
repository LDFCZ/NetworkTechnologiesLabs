package ru.nsu.ccfit.lopatkin;

public class MulticastMessage {
    private String host;

    private int port;

    private String text;

    public MulticastMessage(String host, int port, String text) {
        this.host = host;
        this.port = port;
        this.text = text;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
