package com.wonders.web.controller.test;

/**
 * 单例-懒汉模式
 *
 * Created by c_liqingdong on 2017/5/31.
 */
public class Singleton {

    //1、懒汉模式，私有实例在第一次获取类实例的时候创建
    private static Singleton instance;

    //2、构造函数私有化，不允许外界调用创建实例
    private Singleton() {

    }

    //3、定义实例获取的公有方法
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

}
