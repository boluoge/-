package com.chenway.webserver.utlis;
import com.chenway.webserver.dao.HiveDao;
import org.springframework.data.redis.core.RedisTemplate;

public class HiveUtils {



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

    //hive入口
    public static String getOrSetData(RedisTemplate redisTemplate,String name,HiveDao hiveDao,String args){
        String data="";
        if(nameIsInRedis(redisTemplate,name)){
            data = getDataInRedisWithName(redisTemplate,name);
            System.out.println("get "+name+" in redis");
        }else {
            setDataInRedisWithName(redisTemplate,name,executeMethods(hiveDao,name,args));
            data = getDataInRedisWithName(redisTemplate,name);
            System.out.println("set and get "+name+" in redis");
        }

        return data;
    }


    //判断方法
    public static String executeMethods(HiveDao hiveDao,String methodName,String arg){
        if(methodName.equals("queryAllData")){
            String str=String2Json.list2Json(hiveDao.queryAllData());
            System.out.println(str);
           return str;
        }else if (methodName.equals("queryDataByMinute")){
            return String2Json.list2Json(hiveDao.queryDataByMinute(arg));

        }else if (methodName.equals("queryDataByHour")){
            return String2Json.list2Json(hiveDao.queryDataByHour(arg));

        }else if (methodName.equals("queryDataByDay")){
            return String2Json.list2Json(hiveDao.queryDataByDay(arg));

        }else if (methodName.equals("queryDataByMonth")){
            return String2Json.list2Json(hiveDao.queryDataByMonth(arg));

        }else if (methodName.equals("queryDataByYear")){
            return String2Json.list2Json(hiveDao.queryDataByYear(arg));

        }else if (methodName.equals("queryCategoryNumber")){
            String str=String2Json.list2Json(hiveDao.queryCategoryNumber());
            System.out.println(str);
            return str;
        }else if (methodName.equals("queryCarNumber")){
            String str = hiveDao.queryCarNumber().get(0).get("c0").toString();
            System.out.println("c0--->"+str);
            return str;
        }else if (methodName.equals("queryHistoryCars")){
            String str = hiveDao.queryHistoryCars().get(0).get("c0").toString();
            System.out.println("c0--->history"+str);
            return str;
        }
        return "";
    }

}
