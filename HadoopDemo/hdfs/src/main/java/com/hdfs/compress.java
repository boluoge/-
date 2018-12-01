package com.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.*;

public class compress {
    @Test
    public void deflateCompress() throws IOException {
        Class[] zipClasses = {
                DeflateCodec.class,
                GzipCodec.class,
                BZip2Codec.class,
                Lz4Codec.class,
                SnappyCodec.class
        };
        for (Class c : zipClasses) {
            zip(c);
        }
    }

    public void zip(Class codecClass) throws IOException {
        long start = System.currentTimeMillis();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, new Configuration());

        FileOutputStream fileOutputStream = new FileOutputStream("e:/data/b" + codec.getDefaultExtension());
        CompressionOutputStream outputStream = codec.createOutputStream(fileOutputStream);
        IOUtils.copyBytes(new FileInputStream("e:/data/word.txt"), outputStream, 1024);
        outputStream.close();
        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));

    }

    public void unzip(Class codecClass) throws IOException {
        long start = System.currentTimeMillis();

        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, new Configuration());
        FileInputStream fileInputStream = new FileInputStream("e:/data/b");
        CompressionInputStream inputStream = codec.createInputStream(fileInputStream);
        IOUtils.copyBytes(inputStream, new FileOutputStream("d:/data/b" + codec.getDefaultExtension()), 1024);
        inputStream.close();
        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));

    }
}
