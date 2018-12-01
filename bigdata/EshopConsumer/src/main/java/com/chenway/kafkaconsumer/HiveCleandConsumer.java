package com.chenway.kafkaconsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.io.IOException;
import java.util.*;

/*
原生数据消费者
 */
public class HiveCleandConsumer {
    private final ConsumerConnector consumerConnector;
    private final String topic = "eshop";
    private static HDFSWriter hdfsWriter = new HDFSWriter();

    public HiveCleandConsumer() {  //配置信息
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "s128:2181,s129:2181,s130:2181");
        properties.put("group.id", "gg2");
        properties.put("auto.offset.reset", "smallest");
        properties.put("zookeeper.session.timeout.ms", "500");
        properties.put("zookeeper.sync.time.ms", "250");
        properties.put("auto.commit.interval.ms", "1000");
        //创建消费者连接器
        consumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));

    }

    /*
    处理log
     */
    public void processMessage() {
        // 指定消费的主题
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(1));

        // 消费的消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumerConnector.createMessageStreams(topicCount);

        // 的到指定主题的消息列表
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);

        for (final KafkaStream stream : streams) {
            //
            ConsumerIterator<byte[], byte[]> consumerIte = stream.iterator();
            MyFSDataOutputStream out = null;
            String prePath = "";
            //迭代日志消息
            while (consumerIte.hasNext()) {
                byte[] msg = consumerIte.next().message();
                String newMsg = null;
                //打印
                System.out.println(new String(msg));
                String log = new String(msg);
                String arr[] = StringUtil.splitLog(log);
                if (arr == null || arr.length < 10) {
                    continue;
                }
                //进行清洗
                String request = arr[4];
                String[] regArr = request.split(" ");
                if (regArr != null && regArr.length == 3) {
                    if (regArr[1].endsWith(".html")) {
                        newMsg = StringUtil.arr2Str(arr, ",");
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
                //获取主机名
                String hostname = StringUtil.getHostname(arr);
                //
                //取出日期对象
                Date reqDate = StringUtil.str2Date(arr);
                //得到日历对象
                Calendar c = Calendar.getInstance();
                //设置Date时间
                c.setTime(reqDate);
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH) + 1;
                int d = c.get(Calendar.DAY_OF_MONTH);
                int h = c.get(Calendar.HOUR_OF_DAY);
                int mi = c.get(Calendar.MINUTE);

                //path
                String rawPath = "/user/hive/warehouse/eshop.db/logs/year=" + y
                        + "/month=" + m
                        + "/day=" + d
                        + "/hour=" + h
                        + "/minute=" + mi
                        + "/" + hostname + ".log";
                try {
                    if (!rawPath.equals(prePath)) {
                        if (out != null) {
                            out.release();
                            out = null;
                        }
                        prePath = rawPath;
                        out = (MyFSDataOutputStream) HDFSOutputStreamPool.getInstance().takeOutputStream(rawPath);
                    }
                    out.write(newMsg.getBytes());
                    out.write("\r\n".getBytes());
                    out.hflush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                //写入数据
//                hdfsWriter.writeLog2HDFS(rawPath,msg);
            }
        }
    }

}
