package ru.nsu.ccfit.lopatkin.lab2.server.utils;

import org.apache.log4j.Logger;

import java.util.TimerTask;

public class AliveClientTasksChecker extends TimerTask {

    private static final Logger logger = Logger.getLogger(AliveClientTasksChecker.class);

    private final ClientTasksContext clientTasksContext;

    public AliveClientTasksChecker(ClientTasksContext clientTasksContext) {
        this.clientTasksContext = clientTasksContext;
    }

    @Override
    public void run() {
        logger.info("Task cleaner started");
        this.clientTasksContext.removeDeadClientTasks();
        logger.info("Task cleaner finished");
    }
}
