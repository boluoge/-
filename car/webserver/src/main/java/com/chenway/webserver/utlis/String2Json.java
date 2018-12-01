package com.chenway.webserver.utlis;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class String2Json {

    public static String list2Json(List<Map<String, Object>> mapList){
        String jsonStr = JSON.toJSONString(mapList);
        return  jsonStr;
    }


}
