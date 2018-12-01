package com.chenway.kafkaconsumer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/*
hdfs写入器
 */
public class HDFSWriter {

    /*
    写入log到hdfs文件
    hdfs://mycluster/eshop/2018/07/18/s128.log | s129.log | s130.log
     */
    public void writeLog2HDFS(String path, byte[] log) {

        try {
            //得到我们的装饰流
            FSDataOutputStream out = HDFSOutputStreamPool.getInstance().takeOutputStream(path);
            out.write(log);
            out.write("\r\n".getBytes());
            out.hsync();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FSDataInputStream in = fileSystem.open(new Path("hdfs://mycluster/user/centos/words.txt"));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        IOUtils.copyBytes(in,baos,1024);
//        System.out.println(new String(baos.toByteArray()));
    }

}
