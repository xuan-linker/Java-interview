package com.xlccc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Linker
 * @date 10/12/2023 10:36 PM
 * @description：
 * 实现 Redis 的 IO 多路复用，可以提高 Redis 的性能和并发连接数。Redis 的 IO 多路复用分为以下两种方式：
 *
 * select() 多路复用
 * 在 Redis 2.0 版本之前，Redis 使用了 select() 系统调用来实现 IO 多路复用。select() 函数可以同时监听多个文件描述符（包括套接字），并在有事件发生时返回它们的状态。
 * 在 Redis 中，每个客户端连接都对应一个套接字，Redis 通过 select() 函数来管理这些套接字。
 * 具体实现方式为，Redis 在主循环中通过 select() 函数等待客户端请求，并在有请求到达时进行处理。
 * 由于 select() 函数效率较低，Redis 的性能受到了一定的限制。因此，在 Redis 2.0 之后，Redis 开始采用更高效的 epoll() 系统调用。
 *
 * epoll() 多路复用
 * 从 Redis 2.0 开始，Redis 使用 epoll() 系统调用来实现 IO 多路复用。epoll() 函数是 Linux 系统中专门为高并发网络应用设计的事件通知机制，它使用事件驱动的方式来实现 IO 多路复用。
 * 相对于 select() 函数，epoll() 函数效率更高、并且支持更多的文件描述符。
 * 具体实现方式为，Redis 在主循环中调用 epoll() 函数等待客户端请求，并在有请求到达时进行处理。
 * 为了避免 epoll() 函数的瓶颈，Redis 还使用了其他优化方式，例如设置 TCP_NODELAY 选项来禁用 Nagle 算法、使用 SO_REUSEPORT 选项来启用端口共享等。
 * 需要注意的是，Redis 的 IO 多路复用通常是由 Redis 服务器本身完成的，对于客户端应用程序来说，只需要调用 Redis 提供的 API 并发送请求即可。
 * 同时，Redis 提供的连接池和线程池等机制也能够帮助提高并发性能。
 */
public class RedisIOMultiplexingDemo {
    /**
     * 首先，我们导入了需要使用的jedis包，这是一个Redis的Java客户端。它允许Java程序与Redis进行通信。
     * 在main方法中，我们创建了一个Jedis对象，用于与Redis建立连接。
     * 然后，我们定义了一个匿名内部类JedisPubSub，它继承自redis.clients.jedis.JedisPubSub。在这个类中，我们覆盖了一些方法，如onMessage、onPMessage等，以处理接收到的订阅消息。
     * 接下来，我们创建了一个新的线程并启动它，在这个线程中执行真正的订阅操作。在这个线程中，我们调用jedis.subscribe方法，传入我们自定义的JedisPubSub对象和要订阅的通道。在这个示例中，我们订阅了一个名为"channel"的通道。
     * 在订阅线程启动后，我们再创建一个新的线程并启动它，在这个线程中执行发布操作。在这个线程中，我们等待一段时间，然后使用jedis.publish方法向"channel"通道发布一条消息。
     * 在发布完消息之后，我们通过调用jedis.disconnect方法关闭与Redis的连接，并在控制台打印出"Publisher disconnected"的消息。
     * 最后，我们使用Thread.sleep方法使主线程暂停一段时间，以便观察订阅线程接收到的消息。
     * 这个示例展示了基本的发布-订阅模式，其中一个线程进行订阅操作，另一个线程进行发布操作。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Jedis jedisSubscriber = new Jedis("localhost", 6379);
        Jedis jedisPublisher = new Jedis("localhost", 6379);

        // 订阅监听器
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("接收到消息：" + message);
            }
        };

        // 开启订阅线程
        Thread subscribeThread = new Thread(() -> jedisSubscriber.subscribe(jedisPubSub, "channel"));
        subscribeThread.start();

        // 发布消息
        Thread publishThread = new Thread(() -> {
            try {
                Thread.sleep(1000);  // 等待订阅线程启动
                for (int i = 0; i < 5; i++) {
                    jedisPublisher.publish("channel", "消息" + i);  // 使用另一个客户端连接进行消息发布
                    Thread.sleep(1000);
                }
                jedisPubSub.unsubscribe();  // 在订阅线程中调用unsubscribe方法,取消订阅
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        publishThread.start();

        // 等待订阅线程和发布线程结束
        subscribeThread.join();
        publishThread.join();

        jedisSubscriber.close();
        jedisPublisher.close();
    }
}
