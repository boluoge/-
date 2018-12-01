package com.chenway.webserver.controller;

import com.chenway.webserver.service.HiveService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/car")
public class HiveController {

    @Autowired
    private HiveService hiveService;


    @RequestMapping(value = "/hive/queryAllData",produces = "application/json;charset=UTF-8")
    public String queryAllData(HttpServletRequest request){

        return hiveService.queryAllData();
    }

    @RequestMapping(value = "/hive/queryDataByMinute",produces = "application/json;charset=UTF-8")
    public String queryDataByMinute(){
        return hiveService.queryDataByMinute("2");
    }

    @RequestMapping(value = "/hive/queryDataByHour",produces = "application/json;charset=UTF-8")
    public String queryDataByHour(){
        return hiveService.queryDataByHour("10");
    }

    @RequestMapping(value = "/hive/queryDataByDay",produces = "application/json;charset=UTF-8")
    public String queryDataByDay(){
        return hiveService.queryDataByDay("26");
    }

    @RequestMapping(value = "/hive/queryDataByMonth",produces = "application/json;charset=UTF-8")
    public String queryDataByMonth(){
        return hiveService.queryDataByMonth("9");
    }

    @RequestMapping(value = "/hive/queryDataByYear",produces = "application/json;charset=UTF-8")
    public String queryDataByYear(){
        return hiveService.queryDataByYear("2018");
    }

    @RequestMapping(value = "/hive/queryCategoryNumber",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryCategoryNumber(){
//        Gson gson = new Gson();
//        JsonObject obj = gson.fromJson(hiveService.queryCategoryNumber(),JsonObject.class);
//        return obj;
        return hiveService.queryCategoryNumber();
//        Gson gson = new Gson();
//        JsonParser parser = new JsonParser();
//        JsonArray jarray = parser.parse(hiveService.queryCategoryNumber()).getAsJsonArray();
//        return jarray;
    }

    @RequestMapping(value = "/hive/queryCarNumber",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryCarNumber(){
        return hiveService.queryCarNumber();
    }

    @RequestMapping(value = "/hive/queryHistoryCars",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryHistoryCars(){
        return hiveService.queryHistoryCars();
    }
}
