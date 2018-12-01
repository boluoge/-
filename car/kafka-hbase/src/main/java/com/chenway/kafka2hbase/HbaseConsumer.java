package com.chenway.kafka2hbase;

import com.chenway.common.utils.PropertiesUtil;
import com.chenway.kafka2hbase.dao.HbaseConsumerDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class HbaseConsumer {

    public static void main(String[] args) {
        HbaseConsumerDao dao = new HbaseConsumerDao();

        Properties props = new Properties();
        props.put("bootstrap.servers", PropertiesUtil.getProp("bootstrap.servers"));
        props.put("group.id", PropertiesUtil.getProp("group.id"));
        props.put("enable.auto.commit", PropertiesUtil.getProp("enable.auto.commit"));
        props.put("auto.commit.interval.ms",  PropertiesUtil.getProp("auto.commit.interval.ms"));
      //  props.put("auto.offset.reset",  PropertiesUtil.getProp("auto.offset.reset"));
        props.put("zookeeper.session.timeout.ms",  PropertiesUtil.getProp("zookeeper.session.timeout.ms"));
        props.put("zookeeper.sync.time.ms",  PropertiesUtil.getProp("zookeeper.sync.time.ms"));
        props.put("key.deserializer", PropertiesUtil.getProp("key.deserializer"));
        props.put("value.deserializer", PropertiesUtil.getProp("value.deserializer"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("car"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                dao.put(record.value());
                System.out.println("hbase-->"+record);
            }
        }

    }
}
