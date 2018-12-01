package com.chenway.webserver.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(HBaseProperties.class)
public class HBaseConfig {

    private  static HBaseProperties properties;
    public static Connection connection = null;
    public HBaseConfig(HBaseProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HbaseTemplate hbaseTemplate() {
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        hbaseTemplate.setConfiguration(configuration());
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

    public static  org.apache.hadoop.conf.Configuration configuration() {

        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();

        Map<String, String> config = properties.getConfig();
        Set<String> keySet = config.keySet();
        for (String key : keySet) {
            configuration.set(key, config.get(key));
        }

        return configuration;
    }

    public  static Connection getConnection(){
        try {
            connection = ConnectionFactory.createConnection(configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}