package com.chenway.springbootinterceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
创建一个名叫做SessionInterceptor的拦截器实体类，实现SpringMVC内部接口HandlerInterceptor，
并且添加如果没有session状态直接跳转到/user/login_view地址也就是我们对应的login.jsp页面，
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录不做拦截
        if(request.getRequestURI().equals("/user/login")||request.getRequestURI().equals("/user/login_view")){
            System.out.println(request.getRequestURI());
            return true;
        }
        //验证session是否存在
        Object session_user = request.getSession().getAttribute("session_user");
        if(session_user==null){
            System.out.println(request.getRequestURI());
            response.sendRedirect("/user/login_view");
            return  false;
        }
        System.out.println(request.getRequestURI());
        return true;
    }
}
