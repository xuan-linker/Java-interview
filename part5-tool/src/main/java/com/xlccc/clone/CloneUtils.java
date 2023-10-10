package com.xlccc.clone;

import java.io.*;

/**
 * @author Linker
 * @date 10/10/2023 11:27 PM
 * @description：深层拷贝（Deep Copy）
 * 1. 为什么使用深拷贝
 * Object类提供的clone()方法是浅层拷贝（Shallow Copy），它只复制了原始对象中各个字段的引用，而不是复制它们所指向的对象。
 * 因此，对于原始对象中某一个字段所引用的可变对象，如果在克隆后的对象中对这个对象进行修改，那么原始对象也会受到影响。
 *
 * 2. 如何实现深拷贝
 * 传入的对象序列化成字节数组，然后再将字节数组反序列化为一个新的对象。
 * 由于序列化和反序列化过程中使用了深层拷贝，因此返回的对象与原始对象完全独立，对其进行修改不会影响到原始对象
 */
public class CloneUtils {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T object) {
        T clone = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clone = (T) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clone;
    }
}