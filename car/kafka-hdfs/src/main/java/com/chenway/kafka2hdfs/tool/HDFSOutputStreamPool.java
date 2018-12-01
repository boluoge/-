package com.chenway.kafka2hdfs.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HDFSOutputStreamPool {

    private static FileSystem fs;

    //池
    private  Map<String,FSDataOutputStream> pool = new HashMap<String, FSDataOutputStream>();

    //单例
    private static HDFSOutputStreamPool instance;


    public HDFSOutputStreamPool(){
        try {
        Configuration configuration = new Configuration();
        configuration.setBoolean("dfs.support.append", true);
         fs = FileSystem.get(configuration);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HDFSOutputStreamPool getInstance(){
        if(instance==null){
            instance = new HDFSOutputStreamPool();
        }
        return instance;
    }

    /**
     * 回收流
     */
    public synchronized void putBack(String path,FSDataOutputStream out){
        pool.put(path,out) ;
    }

    /**
     * 释放池子
     */
    public synchronized void releasePool(){
        try{
            for(FSDataOutputStream o : pool.values()){
                o.close();
            }
            pool.clear();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过路径得到对应的输出流
     */
    public synchronized FSDataOutputStream takeOutputStream(String path) {
        try{
            FSDataOutputStream out = pool.remove(path);
            if(out == null){
                Path p = new Path(path);
                if(!fs.exists(p)){
                    fs.createNewFile(p);
                }
                out = fs.append(p);
            }
            //转换成自己的流
            return new MyFSDataOutputStream(path,out,this) ;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null ;
    }

}
