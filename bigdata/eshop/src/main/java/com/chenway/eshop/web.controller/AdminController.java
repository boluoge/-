package com.chenway.eshop.web.controller;

import com.chenway.eshop.model.User;
import com.chenway.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/admin/userlist", method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> allUsers = userService.findAllEntities();
        model.addAttribute("allUsers", allUsers);
        return "userList";
    }

    @RequestMapping(value = "/admin/delUser", method = RequestMethod.GET)
    public String delUser(@RequestParam("uid") int uid) {
        User user = new User();
        user.setId(uid);
        userService.deleteEntity(user);
        return "redirect:/admin/userlist";
    }


    @RequestMapping(value = "/admin/viewUser", method = RequestMethod.GET)
    public String viewUser(@RequestParam("uid") int uid, Model model) {
        User user = userService.getEntity(uid);
        model.addAttribute("user", user);
        return "viewUser";
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam("uid") int uid, Model model) {
        User user = userService.getEntity(uid);
        model.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
    public String updateUser(User user) {
        userService.updateEntity(user);
        return "redirect:/admin/userlist";
    }

}
