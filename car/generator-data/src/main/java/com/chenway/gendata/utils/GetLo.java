package com.chenway.gendata.utils;



import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetLo {
    // key
    private static final String KEY = "XTVBZ-RB423-IRA3E-YXM2N-2K5WE-E3FC4";

    public static Map<String, Object> getLocation(String lng, String lat) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 参数解释：lng：经度，lat：维度。KEY：腾讯地图key，get_poi：返回状态。1返回，0不返回
        String urlString = "https://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + KEY + "&get_poi=1";
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            // 腾讯地图使用GET
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            // 获取地址解析结果
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }

        // 转JSON格式
        JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("result");
        // 获取地址（行政区划信息） 包含有国籍，省份，城市
        JSONObject adInfo = jsonObject.getJSONObject("address_component");
        resultMap.put("nation", adInfo.get("nation"));
        resultMap.put("province", adInfo.get("province"));
        resultMap.put("city", adInfo.get("city"));
        resultMap.put("district", adInfo.get("district"));
        resultMap.put("street", adInfo.get("street"));
        resultMap.put("street_number", adInfo.get("street_number"));
        return resultMap;
    }

    public static void main(String[] args) {

        // 测试
        String lng = "113.50842750442814";//经度
        String lat = "22.92276782120176";//维度
        Map<String, Object> map = getLocation(lng, lat);
        System.out.println(map);
        System.out.println("nation" + map.get("nation"));
        System.out.println("province" + map.get("province"));
        System.out.println("city" + map.get("city"));
        System.out.println("district" + map.get("district"));
        System.out.println("street" + map.get("street"));
        System.out.println("street_number" + map.get("street_number"));




    }
}

