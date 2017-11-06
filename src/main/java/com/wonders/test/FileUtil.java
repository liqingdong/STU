package com.wonders.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
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
 * Created by wangkang on 2017/2/22.
 */
public class FileUtil {

    // 定义一个线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    private static final String separator = File.separator;

    /**
     * 文件夹拷贝(同步操作为单线程操作)
     * @param src 源文件夹
     * @param des 目标文件夹
     * @param async 是否异步
     * @throws Exception
     */
    public static void copyDirectory (String src, String des, boolean async)throws Exception {
        File file1=new File(src);
        File[] fs=file1.listFiles();
        File file2=new File(des);
        if(!file2.exists()){
            file2.mkdirs();
        }
        for (File f : fs) {
            if(f.isFile()){
                if(async){
                    asyncFileCopy(f.getPath(), des + separator + f.getName()); //调用文件拷贝的方法
                }else{
                    fileCopy(f.getPath(), des + separator + f.getName()); //调用文件拷贝的方法
                }
            }else if(f.isDirectory()){
                copyDirectory(f.getPath(), des + separator + f.getName(), async);
            }
        }
    }

    /**
     * 多线程同步文件夹拷贝
     * @param src 源文件夹
     * @param des 目标文件夹
     * @throws Exception
     */
    public static void copyDirectory (String src, String des)throws Exception {
        List<Callable<Object>> callableList = new ArrayList<Callable<Object>>();
        copyDirectory0(src , des , callableList);
        if(callableList.size() > 0){
            executorService.invokeAll(callableList);
        }
    }

    private static void copyDirectory0 (String src, String des, List<Callable<Object>> callableList)throws Exception {
        File file1=new File(src);
        File[] fs=file1.listFiles();
        File file2=new File(des);
        if(!file2.exists()){
            file2.mkdirs();
        }
        for (File f : fs) {
            if(f.isFile()){
                Callable callable = callableFileCopy(f.getPath(), des + separator + f.getName());//调用文件拷贝的方法
                callableList.add(callable);
            }else if(f.isDirectory()){
                copyDirectory0(f.getPath(), des + separator + f.getName(), callableList);
            }
        }
    }

    /**
     * 异步文件拷贝的方法
     * @param src  源文件
     * @param des  目标文件
     */
    public static void asyncFileCopy(String src,String des) throws Exception{
        FileCopyTask fileCopyTask = new FileCopyTask(src , des);
        FutureTask<Object> task = new FutureTask<Object>(fileCopyTask);
        executorService.execute(task);
    }

    /**
     * 文件拷贝的方法 1.7新特性
     * @param src  源文件
     * @param des  目标文件
     */
    public static void newFileCopy(String src, String des) throws Exception{
        Path sourcePath = Paths.get(src);
        Path targetPath = Paths.get(des);
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("源文件 : " + src + ",目标文件 : " + des);
    }

    /**
     * 文件拷贝的方法
     * @param src  源文件
     * @param des  目标文件
     */
    public static void fileCopy(String src, String des) throws Exception{
        FileInputStream in = null;
        FileOutputStream out = null;
        FileChannel inFc = null;
        FileChannel outFC = null;
        try {
            File srcFile = new File(src);
            File destFile = new File(des);
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);

            inFc = in.getChannel();
            outFC = out.getChannel();

            inFc.transferTo(0, inFc.size(), outFC);
            System.out.println("源文件 : " + src + ",目标文件 : " + des);
        }catch (Exception e){
            throw e;
        }finally {
            try {
                if(inFc != null){
                    inFc.close();
                }
                if(outFC != null){
                    outFC.close();
                }
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                throw e;
            }
        }
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *  @param filepath  要删除的目录或文件
     *  @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFile(String filepath) {
        File file = new File(filepath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return file.delete();
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(filepath);
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   filepath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String filepath) {
        File dirFile = new File(filepath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 异步文件拷贝的方法
     * @param src  源文件
     * @param des  目标文件
     * @return Callable 异步回调接口
     */
    public static Callable callableFileCopy(String src,String des) throws Exception{
        FileCopyTask fileCopyTask = new FileCopyTask(src , des);
        return fileCopyTask;
    }

    private static class FileCopyTask implements Callable<Object>{

        private String src;//源文件地址

        private String des;//目标文件地址

        public FileCopyTask(String src,String des){
            this.src = src;
            this.des = des;
        }

        @Override
        public Object call() throws Exception {
            Path sourcePath = Paths.get(src);
            Path targetPath = Paths.get(des);
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("源文件 : " + src + ",目标文件 : " + des);
            return null;
        }
    }
}