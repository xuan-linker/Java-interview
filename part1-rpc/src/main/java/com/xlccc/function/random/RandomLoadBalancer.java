package com.xlccc.function.random;

import java.util.List;
import java.util.Random;

/**
 * @author Linker
 * @date 10/10/2023 11:49 PM
 * @description：随机算法（Random Load Balancing）
 * 随机算法是一种简单直观的负载均衡算法，它根据权重随机选择一个可用的服务节点。
 */
public class RandomLoadBalancer {
    private final List<String> servers;
    private final Random random = new Random();

    public RandomLoadBalancer(List<String> servers) {
        this.servers = servers;
    }

    public String getNextServer() {
        int index = random.nextInt(servers.size());
        return servers.get(index);
    }
}