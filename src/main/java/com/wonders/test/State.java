package com.wonders.test;

/**
 * Description:抽象状态类，定义一个接口以封装与Context的一个特定状态相关的行为
 * <p>
 * Created by liqingdong on 2017/9/26.
 */
public abstract class State {

    public abstract void Handle(Context context);
}
