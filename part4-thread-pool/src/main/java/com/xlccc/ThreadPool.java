package com.xlccc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Linker
 * @date 10/10/2023 11:09 PM
 * @description：
 */
public class ThreadPool {
    private final int nThreads;  // 线程池中核心线程数
    private final PoolWorker[] threads;  // 线程数组
    private final BlockingQueue<Runnable> queue;  // 任务队列

    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue<>();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    // 提交任务到线程池
    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                    }

                    task = queue.poll();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool runtime exception: " + e.getMessage());
                }
            }
        }
    }
}
