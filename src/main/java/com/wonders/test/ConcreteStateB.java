package com.wonders.test;

/**
 * Description:具体状态类，每一个子类实现一个与Context的一个状态相关的行为
 * <p>
 * Created by liqingdong on 2017/9/26.
 */
public class ConcreteStateB extends State {

    // 设置ConcreteStateA的下一个状态是ConcreteStateB
    @Override
    public void Handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
