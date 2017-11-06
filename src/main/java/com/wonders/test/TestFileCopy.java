package com.wonders.test;

import java.util.Date;

/**
 * Created by Donge on 2017/2/27.
 */
public class TestFileCopy {
    public static void main(String[] args) {
        String src = "E:\\dbbx";
        String des = "E:\\dbbx2";
        System.out.println("开始："+new Date());
        try {
            FileCopy.asyncCopyDirectory(src,des);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束："+new Date());
    }

}
