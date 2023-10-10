package com.xlccc.clone;

import java.util.ArrayList;

/**
 * @author Linker
 * @date 10/10/2023 11:27 PM
 * @description：
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> clonedList = CloneUtils.clone(list);
        System.out.println(clonedList);  // 输出 [1, 2, 3]
    }
}
