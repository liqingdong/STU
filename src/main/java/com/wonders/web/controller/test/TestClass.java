package com.wonders.web.controller.test;

/**
 * Created by c_liqingdong on 2017/5/31.
 */
public class TestClass {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println("s1与s2"+(s1==s2?"是":"不是")+"同一个对象");
    }
}
