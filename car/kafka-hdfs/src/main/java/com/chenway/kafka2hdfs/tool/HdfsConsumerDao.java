package com.chenway.kafka2hdfs.tool;

import com.chenway.common.utils.PropertiesUtil;
import com.chenway.kafka2hdfs.utils.StringUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class HdfsConsumerDao {

    private static String topic = PropertiesUtil.getProp("htopic");//获取主题
    static Properties props = new Properties();//装载配置信息
    private KafkaConsumer consumer; //kafka消费者连接器
    String prePath = "";  //上次打开流的路径
    MyFSDataOutputStream out = null;

    public HdfsConsumerDao() {  //配置信息

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

//    /**
//     * 处理log
//     */
    public void processLog() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                byte[] msg = record.value().getBytes();//获取从kafka读取的值
                String log = new String(msg);
                String[] arr = StringUtil.splitLog(log);
                if (arr == null || arr.length < 9) {
                    continue;
                }
                //日期串
                String dateStr = arr[3].replace(" ", "/").replace(":", "/").substring(0, 17);
                //存储到hdfs的相关路径
                String rawPath = "/user/centos/car/raw/" + dateStr + "/" + "car.log";
//                String rawPath = "/user/centos/car/raw/2018/09/25/15/23/car.log";
                try {
                    if (!rawPath.equals(prePath)) {
                        if (out != null) {
                            out.release();
                            out = null;
                        }
                        prePath = rawPath;
                        out = (MyFSDataOutputStream) HDFSOutputStreamPool.getInstance().takeOutputStream(rawPath);
                        //要追加的文件流，inpath为文件
                    }
                        //将数据写入hdfs
                        System.out.println("hdfs--->"+log);
                        out.write(msg);
                        out.write("\n".getBytes());
                        out.hflush();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}

