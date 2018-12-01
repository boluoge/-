package com.chenway.webserver.dao.impl;


import com.chenway.webserver.config.HBaseConfig;
import com.chenway.webserver.dao.HbaseDao;
import com.chenway.webserver.utlis.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


@Repository(value = "hbaseDao")
public class HbaseDaoImpl implements HbaseDao {
    private Logger log = LoggerFactory.getLogger(HbaseDaoImpl.class);

//    @Autowired
//    private HBaseConfig hBaseConfig;
    private Connection connection = HBaseConfig.getConnection();

    //获取表
    @Override
    public Table getTable(String tableName) throws Exception {
        return connection.getTable(TableName.valueOf(tableName));

    }

    //查询所有数据
    @Override
    public Map<String, Map<String, String>> getResultScanner(String tableName) {
        Scan scan = new Scan();
        return this.queryData(tableName,scan);
    }

    //查询数据
    @Override
    public Map<String, Map<String, String>> queryData(String tableName, Scan scan) {
        Map<String,Map<String,String>> result = new HashMap<>();
        ResultScanner resultScanner = null;
        Table table=null;
        try {
            table = getTable(tableName);
            resultScanner = table.getScanner(scan);
            for(Result result1:resultScanner) {
                //每一行数据
                Map<String, String> columMap = new HashMap<>();
                String rowKey = null;
                for (Cell cell : result1.listCells()) {
                    if (rowKey == null) {
                        rowKey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                    }
                    columMap.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()), Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                }

                if (rowKey != null) {
                    result.put(rowKey, columMap);
                }
            }
        }catch (Exception e) {
            log.error(MessageFormat.format("遍历查询指定表中的所有数据失败,tableName:{0}"
                    ,tableName),e);
        } finally {
            close(null,resultScanner,table);
        }

        return result;
    }

    //关闭流
    @Override
    public void close(Admin admin, ResultScanner rs, Table table) {
        if(admin != null){
            try {
                admin.close();
            } catch (IOException e) {
                log.error("关闭Admin失败",e);
            }
        }

        if(rs != null){
            rs.close();
        }

        if(table != null){
            try {
                table.close();
            } catch (IOException e) {
                log.error("关闭Table失败",e);
            }
        }
    }

    //根据startRowKey和stopRowKey遍历查询指定表中的所有数据
    @Override
    public Map<String, Map<String, String>> getResultScanner(String tableName, String startRowKey, String stopRowKey) {
        Scan scan = new Scan();
        if(StringUtils.strIsNotNull(startRowKey) && StringUtils.strIsNotNull(stopRowKey)){
            scan.setStartRow(startRowKey.getBytes());
            scan.setStopRow(stopRowKey.getBytes());
        }
        return this.queryData(tableName,scan);
    }

    //通过行前缀过滤器查询数据
    @Override
    public Map<String, Map<String, String>> getResultScannerPrefixFilter(String tableName, String prefix) {
        Scan scan = new Scan();

        if(prefix!=null && prefix!=""){
            Filter filter = new PrefixFilter(Bytes.toBytes(prefix));
            scan.setFilter(filter);
        }

        return this.queryData(tableName,scan);
    }

    //通过列前缀过滤器查询数据
    @Override
    public Map<String, Map<String, String>> getResultScannerColumnPrefixFilter(String tableName, String prefix) {
        Scan scan = new Scan();

        if(prefix!=null && prefix!=""){
            Filter filter = new ColumnPrefixFilter(Bytes.toBytes(prefix));
            scan.setFilter(filter);
        }

        return this.queryData(tableName,scan);
    }

    //查询行键中包含特定字符的数据
    @Override
    public Map<String, Map<String, String>> getResultScannerRowFilter(String tableName, String keyWord) {
        Scan scan = new Scan();
        if(StringUtils.strIsNotNull(keyWord)){
            Filter filter = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL,new SubstringComparator(keyWord));
            scan.setFilter(filter);
        }
        return this.queryData(tableName,scan);
    }

    //查询列名中包含特定字符的数据
    @Override
    public Map<String, Map<String, String>> getResultScannerQualifierFilter(String tableName, String keyWord) {
        Scan scan = new Scan();
        if(StringUtils.strIsNotNull(keyWord)){
            Filter filter = new QualifierFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL,new SubstringComparator(keyWord));
            scan.setFilter(filter);
        }
        return this.queryData(tableName,scan);
    }

}
