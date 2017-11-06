package com.wonders.test.reflex;

import java.lang.reflect.Field;

/**
 * Created by Donge on 2017/3/16.
 */
public class TestReflex {
    public static void main(String[] args) {
        Class clazz = MyObjct.class;
        Field field[] = null;
        field = clazz.getFields();                                       // 获取所有public变量
        System.out.println(field[0].getName() + ":" + field[0].getGenericType());

        try {
            Field field1 = clazz.getDeclaredField("age");          // 获取private变量age
            System.out.println(field1.getName() + ":" + field1.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //Method[] methods = clazz.getMethods();
        //System.out.println(clazz.getSimpleName());
        //for (Method method : methods) {
        //    System.out.println(method.getName());
        //}
    }

}
