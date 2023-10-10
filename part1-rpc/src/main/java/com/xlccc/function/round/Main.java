package com.xlccc.function.round;

import java.util.Arrays;
import java.util.List;

/**
 * @author Linker
 * @date 10/10/2023 11:51 PM
 * @description：
 */
public class Main {
    public static void main(String[] args) {
        List<String> servers = Arrays.asList("server1", "server2", "server3");
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(servers);

        String selectedServer1 = loadBalancer.getNextServer();
        String selectedServer2 = loadBalancer.getNextServer();
        String selectedServer3 = loadBalancer.getNextServer();

        System.out.println(selectedServer1);  // 输出 server1
        System.out.println(selectedServer2);  // 输出 server2
        System.out.println(selectedServer3);  // 输出 server3

    }
}
