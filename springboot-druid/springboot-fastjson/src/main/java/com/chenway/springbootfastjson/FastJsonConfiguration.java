package com.chenway.springbootfastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
FastJson SerializerFeatures
WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
WriteMapNullValue：是否输出值为null的字段,默认为false。
 */
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
      //修改配置返回内容过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue
        );

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //将fastJson添加到视图消息转换器列表内
        converters.add(fastJsonHttpMessageConverter);
    }
}
