package com.chenway.gendata.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetLocationUtil {

    // key
    private static final String KEY = "XTVBZ-RB423-IRA3E-YXM2N-2K5WE-E3FC4";

    /**
     通过经纬度获取位置
     */
    public static String getLocation(String lng, String lat) {

        String urlString = "https://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + KEY + "&get_poi=1";
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();
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
            e.printStackTrace();
        }

        // 转JSON格式
        JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("result");
        // 获取地址（行政区划信息） 包含有，省份，城市
           JSONObject address_component = jsonObject.getJSONObject("address_component");

           String street_number = address_component.get("street_number").toString();
           String street = address_component.get("street").toString();
           if (street_number.equals(street)) {
               street = "";
           }
           stringBuffer.append(address_component.get("province"))
                   .append(address_component.get("city"))
                   .append(address_component.get("district"))
                   .append(street)
                   .append(street_number);

        return stringBuffer.toString();
    }

}
