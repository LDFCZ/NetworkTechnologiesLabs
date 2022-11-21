package ru.nsu.ccfit.lopatkin.snakes.net.messagehandler;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.net.NetNode;
import ru.nsu.ccfit.lopatkin.snakes.net.messages.ErrorMessage;


public interface ErrorMessageHandler {
    void handle(@NotNull NetNode sender, @NotNull ErrorMessage errorMsg);
}
