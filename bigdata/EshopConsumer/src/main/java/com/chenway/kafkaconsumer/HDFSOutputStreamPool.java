package com.chenway.kafkaconsumer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
输出流池
 */
public class HDFSOutputStreamPool {
    private FileSystem fs;
    //存放所有的输出流
    private Map<String, FSDataOutputStream> pool = new HashMap<String, FSDataOutputStream>();
    private static HDFSOutputStreamPool instance;

    private HDFSOutputStreamPool() {
        try {
            Configuration configuration = new Configuration();
            fs = FileSystem.get(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //单例设计模式
    public static HDFSOutputStreamPool getInstance() {
        if (instance == null) {
            instance = new HDFSOutputStreamPool();
        }
        return instance;
    }

    public synchronized FSDataOutputStream takeOutputStream(String path) {
        try {
            FSDataOutputStream out = pool.remove(path);
            if (out == null) {
                Path p = new Path(path);
                if (!fs.exists(p)) { //若不存在就创建
                    fs.createNewFile(p);
                }
                out = fs.append(p);//存在则直接追加

            }
            return new MyFSDataOutputStream(path, out, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void putBack(String path, FSDataOutputStream fsDataOutputStream) {
        pool.put(path, fsDataOutputStream);
    }

    public synchronized void releasePool() {

        try {
            for (FSDataOutputStream out : pool.values()) {
                out.close();
            }
            pool.clear();
            System.out.println("池释放！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


