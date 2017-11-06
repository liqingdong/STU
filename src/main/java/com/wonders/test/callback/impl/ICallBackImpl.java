package com.wonders.test.callback.impl;

import com.wonders.test.callback.ICallBack;

import java.util.Date;

/**
 * Created by Donge on 2017/2/23.
 */
public class ICallBackImpl implements ICallBack {

    @Override
    public void run() {
        System.out.println(new Date());
    }
}
