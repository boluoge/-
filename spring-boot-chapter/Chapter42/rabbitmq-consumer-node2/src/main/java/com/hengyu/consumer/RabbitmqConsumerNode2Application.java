package com.hengyu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息队列消息消费者节点2入口
 * ========================
 *
 * @author 恒宇少年
 * Created with IntelliJ IDEA.
 * Date：2017/11/26
 * Time：15:15
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 */
@SpringBootApplication
public class RabbitmqConsumerNode2Application
{
    static Logger logger = LoggerFactory.getLogger(RabbitmqConsumerNode2Application.class);

    /**
     * rabbitmq消费者启动入口
     * @param args
     */
    public static void main(String[] args)
    {
        SpringApplication.run(RabbitmqConsumerNode2Application.class,args);

        logger.info("【【【【【消息队列-消息消费者节点2启动成功.】】】】】");
    }
}
