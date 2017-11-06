package com.wonders.test.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Donge on 2017/1/17.
 */
@SuppressWarnings("unused")
public class TestQuartz {

    public void execute() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        System.out.println("定时任务开始执行,现在时间是："+sdf.format(date));
    }
}
