package com.xlccc.jdk8;

/**
 * @author Linker
 * @date 10/15/2023 3:11 AM
 * @description：
 * Java 8 中引入了元空间（Metaspace），取代了之前的 PermGen 空间。PermGen 是指持久代空间，用于存放静态文件，如类和方法等。
 *
 * 元空间是一个与堆不同的内存区域，用于存储类元数据（Class Metadata）。类元数据是指 JVM 加载类时需要的信息，如类名、父类、实现的接口、字段信息、方法信息等。
 * 在 Java 8 以前，这些信息被存储在 PermGen 空间中，但 PermGen 空间大小有限，当加载的类太多时，容易导致 PermGen 空间溢出。
 *
 * 元空间不再是固定大小的，而是根据需要动态调整其大小。它使用本地内存（Native Memory）来存储类元数据，因此不再受到 PermGen 空间大小的限制。
 * 同时，元空间还采用了更快的垃圾回收算法，与之前的 Full GC 相比，元空间的 GC 能力更强，可以更快地释放无用的类元数据。
 */
public class Demo7 {
    //加载了大量的类（byte[] 数组），并通过其 getClass() 方法获取类元数据。这些元数据将存储在元空间中。
    public static class MyClass {
        public static void main(String[] args) {
            for (int i = 0; i < 100000; i++) {
                byte[] b = new byte[1024 * 10];
                Class<?> c = b.getClass();
            }
        }
    }
}
