package com.xlccc.function.round;

import java.util.List;

/**
 * @author Linker
 * @date 10/10/2023 11:51 PM
 * @description：
 */
public class RoundRobinLoadBalancer {
    private final List<String> servers;
    private int currentIndex = 0;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = servers;
    }

    public synchronized String getNextServer() {
        if (servers.isEmpty()) {
            return null;
        }

        String selectedServer = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        return selectedServer;
    }
}