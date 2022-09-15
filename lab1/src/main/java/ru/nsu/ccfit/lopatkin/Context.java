package ru.nsu.ccfit.lopatkin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, Boolean> map = new HashMap<>();

    public void updateHostState(@NotNull String host) {
        map.put(host, true);
    }

    public void deleteDeadHosts() {
        int aliveCount = 0;
        for (String host : map.keySet()) {
            if (!map.get(host)) {
                System.out.println("HOST " + host + " IS DEAD :)");
                map.remove(host);
            }
            else {
                map.put(host, false);
                aliveCount++;
            }
        }
        System.out.println("Im not alone! There are "+ aliveCount + " copy of me");
    }

}
