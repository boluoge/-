package com.chenway.springbootfastjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
以上便是我们本章的全部讲解内容，本章主要讲解了SpringBoot项目如何
将返回的消息从内部的Json转换变成fastJson转换，如何添加fastJson的
转换器过滤配置SerializerFeature。因为@ResultControll注解的Controller
本身返回值就是json字符串，我们上述讲解通过两次修改fastJson过滤器配置
的方式见证了fastJson的神奇效果。


 */
@SpringBootApplication
public class SpringbootFastjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFastjsonApplication.class, args);
	}
}
