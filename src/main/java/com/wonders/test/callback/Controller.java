package com.wonders.test.callback;

import java.util.Scanner;

/**
 * Created by Donge on 2017/2/23.
 */
public class Controller {
    public ICallBack CallBackObject = null;// 引用回调对象
    Scanner input = new Scanner(System.in); //读取命令行输入

    public Controller(ICallBack obj) {
        this.CallBackObject = obj;
    }

    public void begin() {
        while (input.next() != null)//判断是否有输入
        {
            CallBackObject.run();
        }
    }
}
