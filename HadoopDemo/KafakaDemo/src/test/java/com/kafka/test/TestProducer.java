package com.kafka.test;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.junit.Test;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class TestProducer {
    /*
    生产者
     */
    @Test
    public void testSend() {
        Properties props = new Properties();
        //broker列表
        props.put("metadata.broker.list", "s129:9092");
        //串行化
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //
        props.put("request.required.acks", "1");

        //创建生产者配置对象
        ProducerConfig config = new ProducerConfig(props);

        //创建生产者
        Producer<String, String> producer = new Producer<String, String>(config);

        KeyedMessage<String, String> msg = new KeyedMessage<String, String>("test3", "100", "hello world tomas100");
        producer.send(msg);
        System.out.println("send over!");

    }

    @Test
    public void testConumser() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "s129:2181");
        properties.put("group.id", "1");
        properties.put("zookeeper.session.timeout.ms", "1000");
        properties.put("zookeeper.sync.time.ms", "500");
        properties.put("auto.commit.interval.ms", "1000");
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("test3", new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties)).createMessageStreams(map);
        List<KafkaStream<byte[], byte[]>> test3 = messageStreams.get("test3");
        for (KafkaStream<byte[], byte[]> stream : test3) {
            ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
            while (iterator.hasNext()) {
                byte[] message = iterator.next().message();
                System.out.println(new String(message));
            }
        }
    }

}
