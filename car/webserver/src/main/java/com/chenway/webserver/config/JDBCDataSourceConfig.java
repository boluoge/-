package com.chenway.webserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(basePackages="com.chenway.webserver.dao")
public class JDBCDataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "mysqlJdbcDataSource")
    @Qualifier("mysqlJdbcDataSource")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("spring.mysql.url"));
        druidDataSource.setDriverClassName(environment.getProperty("spring.mysql.driver-class-name"));
        druidDataSource.setUsername(environment.getProperty("spring.mysql.username"));
        druidDataSource.setPassword(environment.getProperty("spring.mysql.password"));
        return druidDataSource;

    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("mysqlJdbcDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.chenway.webserver.bean");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
            setDatabase(Database.MYSQL);
        }});
        return entityManagerFactoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }
}
