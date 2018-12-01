package com.chenway.kafkaconsumer;

import org.apache.hadoop.fs.FSDataOutputStream;

import java.io.IOException;

/**
 * 装饰流
 */
public class MyFSDataOutputStream extends FSDataOutputStream {
    private String path;
    private FSDataOutputStream out;

    private HDFSOutputStreamPool pool;//池

    public MyFSDataOutputStream(String path, FSDataOutputStream out, HDFSOutputStreamPool pool) throws IOException {
        super(null);
        this.out = out;
        this.pool = pool;
    }

    public void close() {
        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hflush() throws IOException {
        out.hflush();
    }

    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    public void hsync() throws IOException {
        out.hsync();
    }

    /**
     * 回收，放入池
     */
    public void release() {
        pool.putBack(path, this);
    }
}
