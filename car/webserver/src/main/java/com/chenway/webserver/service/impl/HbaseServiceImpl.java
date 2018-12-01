package com.chenway.webserver.service.impl;

import com.chenway.webserver.dao.HbaseDao;
import com.chenway.webserver.service.HbaseService;
import com.chenway.webserver.utlis.HbaseUtils;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HbaseServiceImpl implements HbaseService {
    @Autowired
    private HbaseDao hbaseDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Table getTable(String tableName) throws Exception {
        return hbaseDao.getTable(tableName);
    }

    @Override
    public String getResultScanner(String tableName) {
        return HbaseUtils.getOrSetData(redisTemplate,"getResultScanner",hbaseDao,tableName);
    }

    @Override
    public String queryData(String tableName, Scan scan) {
//        return hbaseDao.queryData(tableName,scan);
        return "";
    }

    @Override
    public void close(Admin admin, ResultScanner rs, Table table) {
         hbaseDao.close(admin,rs,table);
    }

    @Override
    public String getResultScanner(String tableName, String startRowKey, String stopRowKey) {
//        return hbaseDao.getResultScanner(tableName,startRowKey,stopRowKey);
        return "";
    }

    @Override
    public String getResultScannerPrefixFilter(String tableName, String prefix) {
        //   return hbaseDao.getResultScannerPrefixFilter(tableName,prefix);
        return "";
    }

    @Override
    public String getResultScannerColumnPrefixFilter(String tableName, String prefix) {
       // return hbaseDao.getResultScannerColumnPrefixFilter(tableName,prefix);
        return "";
    }

    @Override
    public Map<String, Map<String, String>> getResultScannerRowFilter(String tableName, String keyWord) {
        return hbaseDao.getResultScannerRowFilter(tableName,keyWord);

    }

    @Override
    public String getResultScannerQualifierFilter(String tableName, String keyWord) {
        //   return hbaseDao.getResultScannerQualifierFilter(tableName,keyWord);
        return "";
    }
}
