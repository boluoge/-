package com.hivedemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveApp {
    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection connection = DriverManager.getConnection("jdbc:hive2://s128:10000/myhivedb");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id,name,age from t");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
