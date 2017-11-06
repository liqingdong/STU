package com.wonders.test;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Donge on 2017/2/24.
 */
public class ChannelTest {
    public static boolean copyFileByIO(File srcFile, File targetDir) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(targetDir + File.separator + srcFile.getName());
        byte[] bytes = new byte[10 * 10];
        int length;
        while ((length = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, length);
        }
        fis.close();
        fos.flush();
        fos.close();
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        File file = new File("E:\\dbbx\\20170224\\busi_cpic_clm_fd.txt");
//        File filebak = new File("E:\\dbbxbak");
//        if (!filebak.exists()) {
//            filebak.mkdir();
//        }
//        try {
//            copyFileByIO(file,filebak);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            FileInputStream is = new FileInputStream(file);
//            byte[] bytes = new byte[10 * 10];
//            int length;
//            while ((length = is.read(bytes)) != -1) {
//                System.out.println("#"+length);
//            }
//            System.out.println(length);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("创建线程" + i);
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("启动线程");
//                }
//            };
//            // 在未来某个时间执行给定的命令
//            service.execute(run);
//        }
//        // 关闭启动线程
//        service.shutdown();
//        // 等待子线程结束，再继续执行下面的代码
//        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
//        System.out.println("all thread complete");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {


                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
