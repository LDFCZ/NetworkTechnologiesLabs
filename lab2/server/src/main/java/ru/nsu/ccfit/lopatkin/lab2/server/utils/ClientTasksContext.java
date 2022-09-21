package ru.nsu.ccfit.lopatkin.lab2.server.utils;

import ru.nsu.ccfit.lopatkin.lab2.server.threadpool.ClientTask;

import java.util.LinkedList;
import java.util.List;

public class ClientTasksContext {

    private final List<ClientTask> clientTaskList = new LinkedList<>();

    public void addClientTask(ClientTask clientTask) {
        this.clientTaskList.add(clientTask);
    }

    public void removeDeadClientTasks() {
        for (ClientTask clientTask: clientTaskList) {
            if (!clientTask.checkIsTaskAlive()) {
                clientTask.stopTask();
                clientTaskList.remove(clientTask);
            }
        }
    }
}
