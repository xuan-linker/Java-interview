package com.xlccc;

import redis.clients.jedis.Jedis;

/**
 * @author Linker
 * @date 10/12/2023 10:19 PM
 * @description： 连接Redis时，确保Redis服务器正在运行，并且主机名和端口号与实际环境一致。
 * Jedis在每个操作后会自动释放连接，但最好在合适的时机手动关闭连接，以避免资源泄露。
 * 连接Redis服务器时，可以使用密码进行认证（如果已启用认证机制）。可以通过jedis.auth("password")方法进行身份验证。
 * 在实际应用中，为了提高性能，推荐使用连接池来管理Jedis连接，而不是每次都创建新的连接。
 */
public class RedisExample {
    public static void main(String[] args) {
        // 创建一个Jedis实例
        Jedis jedis = new Jedis("localhost", 6379);

        // 进行Redis操作
        jedis.set("key", "value");
        String value = jedis.get("key");
        System.out.println(value);

        // 关闭连接
        jedis.close();
    }
}
