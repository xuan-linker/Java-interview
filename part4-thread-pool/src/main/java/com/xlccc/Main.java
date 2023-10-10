package com.xlccc;

/**
 * @author Linker
 * @date 10/10/2023 11:09 PM
 * @description：
 */
public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(10);

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            Runnable task = () -> System.out.println("Thread " + Thread.currentThread().getName() + " is executing task " + finalI);
            pool.execute(task);
        }
    }
}
