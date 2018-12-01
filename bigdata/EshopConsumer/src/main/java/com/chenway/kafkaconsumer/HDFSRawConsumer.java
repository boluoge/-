package com.chenway.kafkaconsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.hadoop.fs.FSDataOutputStream;

import java.io.IOException;
import java.util.*;

/*
原生数据消费者
 */
public class HDFSRawConsumer {
    private final ConsumerConnector consumerConnector;
    private final String topic = "eshop";
    private static HDFSWriter hdfsWriter = new HDFSWriter();

    public HDFSRawConsumer() {  //配置信息
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "s128:2181,s129:2181,s130:2181");
        properties.put("group.id", "gg1");
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
                //打印
                System.out.println(new String(msg));
                String log = new String(msg);
                String arr[] = StringUtil.splitLog(log);
                if (arr == null || arr.length < 10) {
                    continue;
                }
                //获取主机名
                String hostname = StringUtil.getHostname(arr);
                //获取时间
                String dateStr = StringUtil.formatYyyyMmDdHhMi(arr);
                //获取存入hdfs路径
                String rawPath = "/user/centos/eshop/raw/" + dateStr + "/" + hostname + ".log";
                try {
                    if (!rawPath.equals(prePath)) {
                        if (out != null) {
                            out.release();
                            out = null;
                        }
                        prePath = rawPath;
                        out = (MyFSDataOutputStream) HDFSOutputStreamPool.getInstance().takeOutputStream(rawPath);
                    }

                    out.write(msg);

                    out.write("\r\n".getBytes());

                    out.hflush();
//                //写入数据
//                hdfsWriter.writeLog2HDFS(rawPath,msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
