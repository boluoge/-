package com.chenway.kafkaconsumer;

import java.util.Timer;

public class ConsumerApp {
    public static void main(String[] args) {
        //开启定时器任务，周期性关闭流
        new Timer().schedule(new CloseFSOutputStreamTask(), 0, 30000);

        //hdfs消费者
        new Thread() {
            public void run() {
                HDFSRawConsumer consumer = new HDFSRawConsumer();
                consumer.processMessage();
            }
        }.start();

        //hive清洗消费者
        new Thread() {
            public void run() {
                HiveCleandConsumer consumer = new HiveCleandConsumer();
                consumer.processMessage();
            }
        }.start();
    }
}