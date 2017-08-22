package com.wonders.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BeanGenericUtil {

    /**
     * 获取接口的泛型类型，如果不存在则返回null
     *
     * @param clazz
     * @return
     */
    public static Class<?> getGenericClass(Class<?> clazz) {
        return getGenericClass(clazz, 0);
    }

    /**
     * 获取接口的泛型类型，如果不存在则返回null
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<?> getGenericClass(Class<?> clazz, int index) {
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            return ((Class<?>) p[index]);
        }
        return null;
    }

}
