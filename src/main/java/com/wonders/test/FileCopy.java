package com.wonders.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Donge on 2017/2/27.
 */
public class FileCopy {

    private static final String separator = File.separator;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 文件夹拷贝(同步操作为单线程操作)
     *
     * @param src  源文件夹
     * @param des  目标文件夹
     * @param sync 是否同步
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public static void CopyDirectory(String src, String des, boolean sync) throws Exception {
        File file1 = new File(src);
        if (!file1.exists()) {
            System.out.println("源文件目录不存在，请核实。。");
            return;
        }
        File[] files = file1.listFiles();

        File file2 = new File(des);
        if (!file2.exists()) {
            file2.mkdirs();
        }

        for (File file : files) {
            if (file.isFile()) {
                if (sync) {
                    syncCopy(file.getAbsolutePath(), des + separator + file.getName());
                } else {
                    asyncCopy(file.getAbsolutePath(), des + separator + file.getName());
                }
            } else {
                CopyDirectory(file.getAbsolutePath(), des + separator + file.getName(), sync);
            }
        }

    }

    /**
     * 异步拷贝文件夹（多线程）
     *
     * @param src  源文件夹
     * @param des  目标文件夹
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public static void asyncCopyDirectory(String src, String des) throws Exception {
        List<Callable<Object>> callableList = new ArrayList<>();
        asyncCopyDirectory_(src, des,callableList);
        if (callableList.size() > 0) {
            executorService.invokeAll(callableList);
        }

    }

    private static void asyncCopyDirectory_(String src, String des, List<Callable<Object>> callableList) throws Exception {
        File file1 = new File(src);
        if (!file1.exists()) {
            System.out.println("源文件目录不存在，请核实");
            return;
        }
        File[] files = file1.listFiles();

        File file2 = new File(des);
        if (!file2.exists()) {
            file2.mkdirs();
        }

        for (File file : files) {
            if (file.isFile()) {
                callableList.add(callableFileCopy(file.getAbsolutePath(), des + separator + file.getName()));
            } else {
                asyncCopyDirectory_(file.getAbsolutePath(), des + separator + file.getName(),callableList);
            }
        }
    }

    /**
     * 异步文件拷贝的方法
     *
     * @param src 源文件
     * @param des 目标文件
     * @return Callable 异步回调接口
     */
    private static Callable callableFileCopy(String src, String des) throws Exception {
        FileCopyTask fileCopyTask = new FileCopyTask(src, des);
        return fileCopyTask;
    }

    /**
     * 同步文件拷贝
     *
     * @param src 源文件地址
     * @param des 目标文件地址
     * @throws Exception
     */
    public static void syncCopy(String src, String des) throws Exception {
        Path sourcePath = Paths.get(src);
        Path targetPath = Paths.get(des);
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 异步文件拷贝
     *
     * @param src 源文件地址
     * @param des 目标文件地址
     * @throws Exception
     */
    public static void asyncCopy(String src, String des) throws Exception {
        FileCopyTask fileCopyTask = new FileCopyTask(src, des);
        FutureTask<Object> task = new FutureTask<>(fileCopyTask);
        executorService.execute(task);
    }

    private static class FileCopyTask implements Callable<Object> {

        private String src; //源文件地址
        private String des; //目标文件地址

        public FileCopyTask(String src, String des) {
            this.src = src;
            this.des = des;
        }

        @Override
        public Object call() throws Exception {
            Path sourcePath = Paths.get(src);
            Path targetPath = Paths.get(des);
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            return null;
        }
    }

}
