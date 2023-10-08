package com.xlccc.simple;

import com.xlccc.simple.service.CalculatorService;

/**
 * @author Linker
 * @date 10/8/2023 11:07 PM
 * @description：
 */
public class ServiceConsumer {
    public static void main(String[] args) {
        CalculatorService calculatorService = RpcFramework.refer(CalculatorService.class, "127.0.0.1", 8888);
        int result = calculatorService.add(10, 20);
        System.out.println("Result: " + result);
    }
}