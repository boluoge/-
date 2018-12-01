package com.chenway.eshop.test;

import com.chenway.eshop.model.User;
import com.chenway.eshop.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {
    @Test
    public void saveUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setName("tommm");
        user.setPassword("123456");
        userService.saveEntity(user);
    }
}
