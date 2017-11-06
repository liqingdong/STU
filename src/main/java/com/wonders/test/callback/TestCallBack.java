package com.wonders.test.callback;

import com.wonders.test.callback.impl.ICallBackImpl;

/**
 * Created by Donge on 2017/2/23.
 */
public class TestCallBack {
    public static void main(String[] args) {
        new Controller(new ICallBackImpl()).begin();
    }
}
