package com.chenway.webserver.controller;

import com.chenway.webserver.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class IndexController {
    @Autowired
    private HiveService hiveService;

    @RequestMapping(value = "/jsp/index",produces = "application/json;charset=UTF-8")
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/jsp/carContrl")
    public String carContrl(){

        return "carContrl";
    }
    @RequestMapping(value = "/jsp/map")
    public String map(){

        return "map";
    }

    @RequestMapping(value = "/jsp/static")
    public String statics(){

        return "static";
    }

    @RequestMapping(value = "/jsp/message")
    public String message(){

        return "message";
    }


    @RequestMapping(value = "/jsp/table1")
    public String table1(){

        return "table1";
    }
}
