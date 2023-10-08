package com.xlccc.simple;

import com.xlccc.simple.service.CalculatorService;
import com.xlccc.simple.service.CalculatorServiceImpl;

/**
 * @author Linker
 * @date 10/8/2023 11:07 PM
 * @description：
 */
public class ServiceProvider {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorServiceImpl();
        RpcFramework.export(calculatorService, 8888);
    }
}