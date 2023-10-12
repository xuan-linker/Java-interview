package com.xlccc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Linker
 * @date 10/12/2023 10:22 PM
 * @description：
 */
public class RedisSentinelExample {
    public static void main(String[] args) {
        // 设置哨兵节点的信息
        Set<String> sentinels = new HashSet<>();
        sentinels.add("sentinel1_ip:port");
        sentinels.add("sentinel2_ip:port");
        sentinels.add("sentinel3_ip:port");

        // 设置连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // 创建JedisSentinelPool实例
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels, poolConfig);

        // 从连接池获取Jedis实例
        Jedis jedis = sentinelPool.getResource();

        // 进行Redis操作
        jedis.set("key", "value");
        String value = jedis.get("key");
        System.out.println(value);

        // 关闭连接
        jedis.close();
        sentinelPool.close();
    }
}
