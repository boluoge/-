package com.chenway.webserver.controller;

import com.chenway.webserver.service.MysqlService;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/car")
public class MysqlController {

    @Autowired
    private MysqlService mysqlService;

    @RequestMapping(value = "/mysql/top5ByKilometer",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String top5ByKilometer(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.top5ByKilometer());
    }

    @RequestMapping(value = "/mysql/top5ByCount",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String top5ByCount(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.top5ByCount());
    }

    @RequestMapping(value = "/mysql/top5ByLocation",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String top5ByLocation(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.top5ByLocation());
    }

    @RequestMapping(value = "/mysql/top5BySpeed",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String top5BySpeed(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.top5BySpeed());
    }

    @RequestMapping(value = "/mysql/findCSN",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findCSN(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.findCSN());
    }

    @RequestMapping(value = "/mysql/findSN",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findSN(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.findSN());
    }

    @RequestMapping(value = "/mysql/findCKN",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findCKN(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.findCKN());
    }

    @RequestMapping(value = "/mysql/findLastLocation",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findLastLocation(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.findLastLocation());
    }

    @RequestMapping(value = "/mysql/findCategoryNum",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findCategoryNum(){
        Gson gson = new Gson();
        return gson.toJson(mysqlService.findCategoryNum());
    }
}
