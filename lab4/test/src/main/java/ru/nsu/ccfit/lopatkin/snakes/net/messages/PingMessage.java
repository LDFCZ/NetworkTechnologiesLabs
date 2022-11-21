package ru.nsu.ccfit.lopatkin.snakes.net.messages;

public class PingMessage extends Message {
    public PingMessage() {
        super(MessageType.PING);
    }
}
