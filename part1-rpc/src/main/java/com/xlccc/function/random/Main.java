package com.xlccc.function.random;

import java.util.Arrays;
import java.util.List;

/**
 * @author Linker
 * @date 10/10/2023 11:50 PM
 * @description：
 */
public class Main {
    public static void main(String[] args) {
        List<String> servers = Arrays.asList("server1", "server2", "server3");
        RandomLoadBalancer loadBalancer = new RandomLoadBalancer(servers);

        String selectedServer = loadBalancer.getNextServer();
        System.out.println(selectedServer);  // 输出随机选择的服务器名
    }
}
