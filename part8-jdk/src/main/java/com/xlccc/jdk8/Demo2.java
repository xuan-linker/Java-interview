package com.xlccc.jdk8;

/**
 * @author Linker
 * @date 10/15/2023 3:02 AM
 * @description：
 * Java 8 中内置了一些常用的函数式接口，比如：
 *
 * Consumer<T>：接收一个参数，无返回值。
 * Supplier<T>：不接收参数，返回一个值。
 * Function<T, R>：接收一个参数，返回一个值。
 * Predicate<T>：接收一个参数，返回一个布尔值。
 */
public class Demo2 {
    @FunctionalInterface
    interface Calculator {
        int calculate(int x, int y);
    }

    public static void main(String[] args) {
        Calculator addition = (x, y) -> x + y;
        Calculator subtraction = (x, y) -> x - y;

        System.out.println(addition.calculate(3, 2)); // 输出 5
        System.out.println(subtraction.calculate(3, 2)); // 输出 1
    }
}
