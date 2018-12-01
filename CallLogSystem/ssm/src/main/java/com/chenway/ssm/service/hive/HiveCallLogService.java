package com.chenway.ssm.service.hive;

import com.chenway.ssm.domain.CallLog;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service("hiveCallLogService")
public class HiveCallLogService {
    private static String url = "jdbc:hive2://s128:10000/myhivedb";
    private static String driverClass = "org.apache.hive.jdbc.HiveDriver";

    static {
        try {
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    查询最近的通话记录，使用hive进行mr查询
     */
    public static CallLog findLastestCallLog(String phoneNum) {
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String sql = "select * from myhivedb.ext_calllogs_in_hbase where id like '%" + phoneNum + "%' order by callTime desc limit 1";
            ResultSet resultSet = statement.executeQuery(sql);
            CallLog callLog = null;
            if (resultSet.next()) {
                callLog = new CallLog();
                callLog.setCaller(resultSet.getString("caller"));
                callLog.setCallee(resultSet.getString("callee"));
                callLog.setCallTime(resultSet.getString("callTime"));
                callLog.setCallDuration(resultSet.getString("callDuration"));

            }
            resultSet.close();
            return callLog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
