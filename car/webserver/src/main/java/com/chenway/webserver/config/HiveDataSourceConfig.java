package com.chenway.webserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class HiveDataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "hiveJdbcDataSource")
    @Qualifier("hiveJdbcDataSource")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("spring.hive.url"));
        druidDataSource.setDriverClassName(environment.getProperty("spring.hive.driver-class-name"));
        druidDataSource.setUsername(environment.getProperty("spring.hive.username"));
        druidDataSource.setPassword(environment.getProperty("spring.hive.password"));
        return druidDataSource;

    }

    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
