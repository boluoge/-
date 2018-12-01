package com.zookeeper.zktest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class Test {
    @org.junit.Test
    public void ls() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("s129:2181", 5000, null);
        List<String> list = zooKeeper.getChildren("/", null);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @org.junit.Test
    public void testWatch() throws IOException, KeeperException, InterruptedException {
        final ZooKeeper zooKeeper = new ZooKeeper("s128:2181", 5000, null);
        Stat stat = new Stat();
        Watcher watcher = null;
        watcher = new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    System.out.println("数据改了！！");
                    zooKeeper.getData("/a", this, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        byte[] data = zooKeeper.getData("/a", watcher, stat);
        System.out.println(new String(data));
        while (true) {
            Thread.sleep(1000);
        }

    }
}
