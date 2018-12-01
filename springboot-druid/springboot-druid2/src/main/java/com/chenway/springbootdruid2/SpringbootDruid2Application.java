package com.chenway.springbootdruid2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
本章主要讲解了SpringBoot整合Druid数据库连接池完成SpringDataJpa
访问MySQL数据，开启了Druid连接池本身自带的数据监控功能，可以清
晰的看到SQL执行以及Session活跃情况，方便优化SQL。
 */
@SpringBootApplication
public class SpringbootDruid2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDruid2Application.class, args);
	}
}
