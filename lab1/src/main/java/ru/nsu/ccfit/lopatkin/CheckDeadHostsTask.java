package ru.nsu.ccfit.lopatkin;

import java.util.TimerTask;

public class CheckDeadHostsTask extends TimerTask {

    private Context context;

    public CheckDeadHostsTask(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        System.out.println("CLEANING RUNNING!");
        context.deleteDeadHosts();
        System.out.println("CLEANING ENDING!");
    }
}
