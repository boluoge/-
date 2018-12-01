package com.chenway.kafka2hdfs.tool;


import com.chenway.common.utils.PropertiesUtil;
import com.chenway.kafka2hdfs.utils.StringUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/*
原生数据消费者
 */
public class HiveCleandConsumer {
    private static String topic = PropertiesUtil.getProp("htopic");//获取主题
    static Properties props = new Properties();//装载配置信息
    private KafkaConsumer consumer; //kafka消费者连接器
    String prePath = "";  //上次打开流的路径
    MyFSDataOutputStream out = null;

    public HiveCleandConsumer() {  //配置信息
        props.put("bootstrap.servers", PropertiesUtil.getProp("hbootstrap.servers"));
        props.put("group.id", PropertiesUtil.getProp("hgroup.id"));
        props.put("enable.auto.commit", PropertiesUtil.getProp("henable.auto.commit"));
        props.put("auto.commit.interval.ms", PropertiesUtil.getProp("hauto.commit.interval.ms"));
        props.put("zookeeper.connect", PropertiesUtil.getProp("hzookeeper.connect"));
        //  props.put("auto.offset.reset",  PropertiesUtil.getProp("hauto.offset.reset"));
        props.put("zookeeper.session.timeout.ms", PropertiesUtil.getProp("hzookeeper.session.timeout.ms"));
        props.put("zookeeper.sync.time.ms", PropertiesUtil.getProp("hzookeeper.sync.time.ms"));
        props.put("key.deserializer", PropertiesUtil.getProp("hkey.deserializer"));
        props.put("value.deserializer", PropertiesUtil.getProp("hvalue.deserializer"));
        //创建消费者连接器
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

    }

    /*
    处理log
     */
    public void processMessage() {
        // 指定消费的主题
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                byte[] msg = record.value().getBytes();//获取从kafka读取的值
                String log = new String(msg);
                String[] arr = StringUtil.splitLog(log);
                if (arr == null || arr.length < 9) {
                    continue;
                }
                //  2018/04/04 45:48:32
                String dataArr[] = arr[3].replace("/", " ").replace(":", " ").split(" ");
               //取出时间
                int y = Integer.parseInt(dataArr[0]);
                int m = Integer.parseInt(dataArr[1]);
                int d = Integer.parseInt(dataArr[2]);
                int h = Integer.parseInt(dataArr[3]);
                int mi =Integer.parseInt(dataArr[4]);

                //path
                String rawPath = "/user/hive/warehouse/car.db/carlogs/year=" + y
                        + "/month=" + m
                        + "/day=" + d
                        + "/hour=" + h
                        + "/minute=" + mi
                        + "/" +  "car.log";
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
                    System.out.println("hive--->"+log);
                    out.write("\n".getBytes());
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
