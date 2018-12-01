package com.chenway.kafkaconsumer;

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
