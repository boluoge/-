package com.chenway.springbootinterceptor.controller;

import com.chenway.springbootinterceptor.dao.UserJPA;
import com.chenway.springbootinterceptor.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/login")
    public String login(User user, HttpServletRequest request){
        boolean flag = true;
        String result="登录成功";
        User one = userJPA.findOne(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("username"), user.getUsername()));
                return null;
            }
        }).get();
        //用户不存在
        if(one==null){
            flag =false;
            result="用户不存在，登录失败";
        }
        else if(!one.getPassword().equals(user.getPassword())){
            flag =false;
            result="用户密码不相符";
        }
        if(flag ){
            request.getSession().setAttribute("session_user", one);
        }
        return result;
    }
}
