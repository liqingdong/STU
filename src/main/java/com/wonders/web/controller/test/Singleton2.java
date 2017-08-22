package com.wonders.web.controller.test;


/**
 * 单例-饿汉模式
 *
 * Created by c_liqingdong on 2017/5/31.
 */
public class Singleton2 {

    // 1、饿汉模式，实例在类加载的时候创建
    private static Singleton2 instance = new Singleton2();

    //2、构造方法私有化
    private Singleton2() {

    }

    //3、定义实例获取的公有方法
    public static Singleton2 getInstance() {
        return instance;
    }
}
