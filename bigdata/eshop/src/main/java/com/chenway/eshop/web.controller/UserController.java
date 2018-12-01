package com.chenway.eshop.web.controller;

import com.chenway.eshop.model.User;
import com.chenway.eshop.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/toRegPage", method = RequestMethod.GET)
    public String toReg() {
        return "userReg";
    }

    @RequestMapping(value = "/doReg", method = RequestMethod.POST)
    public String doReg(User user, HttpServletRequest request, Model model) {
        String confirmPass = request.getParameter("confirmPass");
        if (!user.getPassword().equals(confirmPass)) {
            model.addAttribute("error.password.nosame", "两次密码输入不一致，确认后请重新输入");
            return "userReg";
        }
        boolean b = userService.isRegisted(user.getEmail());
        if (b) {
            model.addAttribute("error.email.registed", "该邮箱已经注册");
            return "userReg";
        }
        userService.saveEntity(user);
        System.out.println("注册成功");

        return "login";
    }

    @RequestMapping(value = "/toLoginPage", method = RequestMethod.GET)
    public String toLoginPage() {
        return "login";
    }

    /*
    User:封装的客户端提交的user信息
    sesssion对象，用来保存登录成功的用户名
    model对象，登录失败向客户端回传失败信息的媒体
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(User user, HttpSession session, Model model) {
        String hql = "from User u where u.name=? and u.password=?";
        List<User> list = userService.findByHQL(hql, user.getName(), user.getPassword());
        if (list == null || list.isEmpty()) {
            System.out.println("失败");
            model.addAttribute("error", "用户名/密码验证失败，请重试！！！");
        } else {
            //验证成功，将name信息保存到session中
            System.out.println("成功!");
            User u = list.get(0);
            session.setAttribute("name", u.getName());
        }
        return "index";
    }
}
