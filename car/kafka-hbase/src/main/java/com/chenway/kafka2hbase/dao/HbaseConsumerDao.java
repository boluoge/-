package com.chenway.kafka2hbase.dao;

import com.chenway.common.utils.PropertiesUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HbaseConsumerDao {

    private static Configuration conf;
    private static Connection connection;
    private static Table table;
    private static DecimalFormat df = new DecimalFormat();
    private int partitions;

    public HbaseConsumerDao(){
        try {
         conf = HBaseConfiguration.create();
         connection = ConnectionFactory.createConnection(conf);
         TableName tableName = TableName.valueOf(PropertiesUtil.getString("table.name"));
         table = connection.getTable(tableName);
         df.applyPattern(PropertiesUtil.getString("hashcode.pattern"));
         partitions = PropertiesUtil.getInt("partition.number");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * put数据到hbase
     */
    public void put(String log){
        if(log.equals("")||log==null){
            return;
        }
        try {
            //解析数据
            String arr[] = log.split(",");
            if(arr!=null && arr.length==9){
                String carId = arr[0];
                String longitude = arr[1];
                String latitude = arr[2];
                String time = arr[3];
                String category=arr[4];
                String statu = arr[5];
                String speed =arr[6];
                String kilometer= arr[7];
                String location=arr[8];

                String rowkey=getRowKey(getHashCode(carId,time),
                        carId,longitude,latitude,time,category,statu,kilometer);
                Put put = new Put(Bytes.toBytes(rowkey));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("speed"),Bytes.toBytes(speed));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("location"),Bytes.toBytes(location));
                table.put(put);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据传入车牌、时间求出哈希值
    public String getHashCode(String carId,String time){
        //取出时间单位,年月日
        String day = time.substring(0,8);
        int hashcode = (Integer.valueOf(getNumber(carId)) ^ Integer.parseInt(getNumber(day))) % partitions;
        return df.format(hashcode);
    }

    //根据传入哈希车牌ID，经度，纬度,时间,状态,里程求出rowkey
    public String getRowKey(String hashCode,String carId,String longitude,String latitude
                            ,String time,String category,String statu,String kilometer){
        return hashCode+","+carId+","+longitude+","+latitude+","+getNumber(time)+","+category+","+statu+","+kilometer;
    }

    //取出数字
    public String getNumber(String string){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }
}
