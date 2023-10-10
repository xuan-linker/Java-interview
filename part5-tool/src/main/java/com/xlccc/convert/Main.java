package com.xlccc.convert;

/**
 * @author Linker
 * @date 10/10/2023 11:39 PM
 * @description：
 */
public class Main {
    public static void main(String[] args) {
        Integer intValue = ConvertUtils.convert("123", Integer.class);
        Long longValue = ConvertUtils.convert(123, Long.class);
        Boolean boolValue = ConvertUtils.convert("true", Boolean.class);

        System.out.println(intValue);   // 输出 123
        System.out.println(longValue);  // 输出 123
        System.out.println(boolValue);  // 输出 true

    }
}
