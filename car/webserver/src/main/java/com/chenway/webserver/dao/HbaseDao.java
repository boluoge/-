package com.chenway.webserver.dao;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

import java.util.Map;


public interface HbaseDao {

    //获取表
    public Table getTable(String tableName) throws Exception;

    //查询所有数据
    public Map<String,Map<String,String>> getResultScanner(String tableName);

    //查询数据
    public Map<String,Map<String,String>> queryData(String tableName, Scan scan);

    //关闭流
    public void close(Admin admin, ResultScanner rs, Table table);

    //根据startRowKey和stopRowKey遍历查询指定表中的所有数据
    public Map<String,Map<String,String>> getResultScanner(String tableName, String startRowKey, String stopRowKey);

    //通过行前缀过滤器查询数据
    public Map<String,Map<String,String>> getResultScannerPrefixFilter(String tableName, String prefix);

    //通过列前缀过滤器查询数据
    public Map<String,Map<String,String>> getResultScannerColumnPrefixFilter(String tableName, String prefix);

    //查询行键中包含特定字符的数据
    public Map<String,Map<String,String>> getResultScannerRowFilter(String tableName, String keyWord);

    //查询列名中包含特定字符的数据
    public Map<String,Map<String,String>> getResultScannerQualifierFilter(String tableName, String keyWord);

}
