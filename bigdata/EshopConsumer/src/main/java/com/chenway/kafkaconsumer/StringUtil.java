package com.chenway.kafkaconsumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil {
    //分
    private static final String token = "\\|\\|\\|"; //分割符

    public static String[] splitLog(String log) { //根据分割符得到数组
        String[] arr = log.split(token);
        return arr;
    }

    public static String getHostname(String[] arr) { //获取主机名
        return arr[0];
    }

    public static String formatYyyyMmDdHhMi(String[] arr) {//获取时间并转换为自己所需要的时间格式
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
            Date d = sdf.parse(arr[3].split(" ")[0]);
            System.out.println(d);
            SimpleDateFormat localSdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm", Locale.US);
            return localSdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    将数据转换为字符串，使用token作为分隔符
     */
    public static String arr2Str(Object[] objects, String token) {
        String str = "";
        for (Object o : objects) {
            str = str + o + token + ",";
        }
        return str.substring(0, str.length() - 1);
    }


    /**
     * 将字符串转成日期对象
     */
    public static Date str2Date(String[] arr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
            Date d = sdf.parse(arr[3].split(" ")[0]);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
