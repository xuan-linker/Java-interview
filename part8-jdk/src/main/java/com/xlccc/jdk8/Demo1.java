package com.xlccc.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Linker
 * @date 10/15/2023 2:43 AM
 * @description： Java SE 8 特性：
 * <p>
 * Lambda 表达式和方法引用
 * 函数式接口
 * 默认方法和静态方法
 * 新的日期和时间 API（java.time 包）
 * 接口中的静态方法和默认方法
 * PermGen 内存被元空间替代
 */
public class Demo1 {
    public static void main(String[] args) {
        // Lambda 表达式示例
        Runnable runnable = () -> {
            System.out.println("This is a runnable task.");
        };
        runnable.run();

        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 7);
        Collections.sort(numbers, (a, b) -> Integer.compare(a, b));

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello " + name));

        // 方法引用示例
        List<String> names2 = Arrays.asList("Alice", "Bob", "Charlie");
        names2.forEach(System.out::println);

        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();


    }
}
