package com.xlccc.convert;

/**
 * @author Linker
 * @date 10/10/2023 11:39 PM
 * @description：
 */
public class ConvertUtils {
    private ConvertUtils() {}

    public static <T> T convert(Object object, Class<T> targetType) {
        if (object == null) {
            return null;
        }

        if (targetType.isAssignableFrom(object.getClass())) {
            return targetType.cast(object);
        }

        if (targetType == String.class) {
            return (T) object.toString();
        }

        if (targetType == Integer.class) {
            return (T) Integer.valueOf(object.toString());
        }

        if (targetType == Long.class) {
            return (T) Long.valueOf(object.toString());
        }

        if (targetType == Float.class) {
            return (T) Float.valueOf(object.toString());
        }

        if (targetType == Double.class) {
            return (T) Double.valueOf(object.toString());
        }

        if (targetType == Boolean.class) {
            return (T) Boolean.valueOf(object.toString());
        }

        throw new IllegalArgumentException("Unsupported target type: " + targetType.getSimpleName());
    }
}
