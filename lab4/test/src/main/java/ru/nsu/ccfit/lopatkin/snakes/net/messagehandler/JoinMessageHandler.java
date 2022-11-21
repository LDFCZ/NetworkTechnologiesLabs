package ru.nsu.ccfit.lopatkin.snakes.net.messagehandler;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.net.NetNode;
import ru.nsu.ccfit.lopatkin.snakes.net.messages.JoinMessage;

public interface JoinMessageHandler {
    void handle(@NotNull NetNode sender, @NotNull JoinMessage joinMsg);
}
