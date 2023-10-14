package com.xlccc.jdk8;

/**
 * @author Linker
 * @date 10/15/2023 3:06 AM
 * @description：
 * 静态方法是在接口中定义的具有静态修饰符的方法，可以直接通过接口名调用。静态方法通常用于工具类，提供一些公共的方法实现。
 */
public class Demo4 {
    interface Utility {
        static String join(String delimiter, String... strings) {
            StringBuilder sb = new StringBuilder();
            for (String s : strings) {
                sb.append(s).append(delimiter);
            }
            return sb.substring(0, sb.length() - delimiter.length());
        }
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie"};
        String joined = Utility.join(", ", names);
        System.out.println(joined); // 输出 "Alice, Bob, Charlie"
    }

}
