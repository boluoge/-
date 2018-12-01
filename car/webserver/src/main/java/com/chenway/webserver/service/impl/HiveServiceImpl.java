package com.chenway.webserver.service.impl;

import com.chenway.webserver.dao.HiveDao;
import com.chenway.webserver.service.HiveService;
import com.chenway.webserver.utlis.HiveUtils;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class HiveServiceImpl implements HiveService {
    @Autowired
    private HiveDao hiveDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String queryAllData() {
       return HiveUtils.getOrSetData(redisTemplate,"queryAllData",hiveDao,"");
       // return String.valueOf(hiveDao.queryAllData());
 }

    @Override
    public String queryDataByMinute(String minute) {
        return HiveUtils.getOrSetData(redisTemplate,"queryDataByMinute",hiveDao,minute);
    }

    @Override
    public String queryDataByHour(String hour) {
        return HiveUtils.getOrSetData(redisTemplate,"queryDataByHour",hiveDao,hour);
    }

    @Override
    public String queryDataByDay(String day) {
        return HiveUtils.getOrSetData(redisTemplate,"queryDataByDay",hiveDao,day);
        }

    @Override
    public String queryDataByMonth(String month) {
        return HiveUtils.getOrSetData(redisTemplate,"queryDataByMonth",hiveDao,month);
    }

    @Override
    public String queryDataByYear(String year) {
        return HiveUtils.getOrSetData(redisTemplate,"queryDataByYear",hiveDao,year);
    }

    @Override
    public String queryCategoryNumber() {
        String str =HiveUtils.getOrSetData(redisTemplate,"queryCategoryNumber",hiveDao,"");
        System.out.println(str);
        return str;
    }

    @Override
    public String queryCarNumber() {
        String str =HiveUtils.getOrSetData(redisTemplate,"queryCarNumber",hiveDao,"");
        System.out.println(str);
        return str;
    }

    @Override
    public String queryHistoryCars() {
         String str =HiveUtils.getOrSetData(redisTemplate,"queryHistoryCars",hiveDao,"");
         System.out.println(str);
         return str;
    }


}
