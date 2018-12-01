package com.chenway.kafka2hdfs;


import com.chenway.kafka2hdfs.tool.CloseFSOutputStreamTask;
import com.chenway.kafka2hdfs.tool.HdfsConsumerDao;
import com.chenway.kafka2hdfs.tool.HiveCleandConsumer;

import java.util.Timer;

public class HdfsConsumer {

    public static void main(String[] args) {
        //开定时器任务，周期性的关闭流
        new Timer().schedule(new CloseFSOutputStreamTask(), 0, 6000000);

        //开启线程启动hdfs消费者
        new Thread() {
            public void run() {
                HdfsConsumerDao consumer = new HdfsConsumerDao();
                consumer.processLog();
            }
        }.start();

//        hive清洗消费者
        new Thread() {
            public void run() {
                HiveCleandConsumer consumer = new HiveCleandConsumer();
                consumer.processMessage();
            }
        }.start();
    }
}
