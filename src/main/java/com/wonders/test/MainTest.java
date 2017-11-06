package com.wonders.test;


/**
 * Description:
 * <p>
 * Created by liqingdong on 2017/9/22.
 */
public class MainTest {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.Request();
        context.Request();
        context.Request();
        context.Request();
    }
}
