package com.chenway.springbootjavamail;

import com.chenway.springbootjavamail.pojo.MailContentTypeEnum;
import com.chenway.springbootjavamail.pojo.MailSender;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception{
        new MailSender()
                .title("i am your pineapple")
                .content("xian nv")
                .contentType(MailContentTypeEnum.TEXT)
                .targets(new ArrayList<String>(){{
                    add("1115126756@qq.com");
                }})
                .send();
    }
}
