package com.chenway.webserver.utlis;
import com.chenway.webserver.dao.HbaseDao;
import com.chenway.webserver.dao.HiveDao;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class HbaseUtils {



    //判断有没有redis中缓存
    public static boolean nameIsInRedis(RedisTemplate redisTemplate,String name){

       if(redisTemplate.opsForValue().get(name)!=null) {
           return true;
       }
        return false;
    }
    //缓存数据
    public static void setDataInRedisWithName(RedisTemplate redisTemplate,String name,String object){
        redisTemplate.opsForValue().set(name,object);
    }

    //获取缓存数据
    public static String getDataInRedisWithName(RedisTemplate redisTemplate,String name){
        return (String) redisTemplate.opsForValue().get(name);
    }

    //hbae入口
    public static String getOrSetData(RedisTemplate redisTemplate, String name, HbaseDao hbaseDao, String args){
        String data="";
        if(nameIsInRedis(redisTemplate,name)){
            data = getDataInRedisWithName(redisTemplate,name);
            System.out.println("get "+name+" in redis");
        }else {
            setDataInRedisWithName(redisTemplate,name,executeMethods(hbaseDao,name,args));
            data = getDataInRedisWithName(redisTemplate,name);
            System.out.println("set and get "+name+" in redis");
        }

        return data;
    }


    //判断方法
    public static String executeMethods(HbaseDao hbaseDao, String methodName, String arg){
        if(methodName.equals("getResultScanner")){
            StringBuffer stringBuffer = new StringBuffer();
            hbaseDao.getResultScanner(arg).forEach((rowkeys,values)->{
                stringBuffer.append(rowkeys);
                stringBuffer.append(","+values.get("location"));
                stringBuffer.append(","+values.get("speed"));
            });
            System.out.println(stringBuffer.toString());
           return stringBuffer.toString();
        }
        return "";
    }

}
