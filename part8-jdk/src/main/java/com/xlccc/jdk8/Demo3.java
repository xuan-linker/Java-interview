package com.xlccc.jdk8;

/**
 * @author Linker
 * @date 10/15/2023 3:04 AM
 * @description：
 * 默认方法是接口中可以有实现的方法。与抽象方法不同，实现类不必实现默认方法。如果实现类需要覆盖默认方法，可以将其作为抽象方法来实现。
 */
public class Demo3 {
    interface Animal {
        default void eat() {
            System.out.println("Animal is eating.");
        }
    }

    static class Dog implements Animal {
        @Override
        public void eat() {
            System.out.println("Dog is eating.");
        }
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.eat(); // 输出 "Dog is eating."
    }

}
