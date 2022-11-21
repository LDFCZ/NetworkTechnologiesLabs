package ru.nsu.ccfit.lopatkin.snakes.net.messagehandler;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.net.NetNode;
import ru.nsu.ccfit.lopatkin.snakes.net.messages.AnnouncementMessage;


public interface AnnouncementMessageHandler {
    void handle(@NotNull NetNode sender, @NotNull AnnouncementMessage announcementMsg);
}
