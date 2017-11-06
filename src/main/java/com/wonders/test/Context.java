package com.wonders.test;

/**
 * Description:维护一个ConcreteState子类的实例，这个实例定义当前的状态。
 * <p>
 * Created by liqingdong on 2017/9/26.
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void Request() {
        state.Handle(this);
    }
}

