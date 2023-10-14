package com.xlccc.jdk8;

/**
 * @author Linker
 * @date 10/15/2023 3:10 AM
 * @description：
 * Java 8 中允许在接口中定义静态方法和默认方法。
 *
 * 静态方法（static）就是类级别的方法，可以使用 接口名.方法名() 的形式调用。这些方法不是实例方法，因此不能访问实例变量或实例方法。
 *
 * 默认方法（default）则是实例方法，具有默认实现。可以在接口中定义一个默认方法，然后该接口的所有实现类都将自动继承该方法。如果实现类重写了该方法，则覆盖默认实现。
 */
public class Demo6 {
    public interface MyInterface {
        // 静态方法
        static void staticMethod() {
            System.out.println("静态方法");
        }

        // 默认方法
        default void defaultMethod() {
            System.out.println("默认方法");
        }
    }

    public static class MyClass implements MyInterface {
        // 实现类中不必覆盖 MyInterface 中的默认方法

        public static void main(String[] args) {
            // 调用静态方法
            MyInterface.staticMethod(); // 输出 "静态方法"

            // 调用默认方法
            MyClass myClass = new MyClass();
            myClass.defaultMethod(); // 输出 "默认方法"
        }
    }

}
