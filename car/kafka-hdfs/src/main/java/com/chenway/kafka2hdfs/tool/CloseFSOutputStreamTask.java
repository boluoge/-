package com.chenway.kafka2hdfs.tool;

import java.util.TimerTask;

/*
关闭线程池
 */
public class CloseFSOutputStreamTask extends TimerTask {
    public void run() {
        HDFSOutputStreamPool pool = HDFSOutputStreamPool.getInstance();
        pool.releasePool();
    }
}
