package com.jdbc.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            //创建连接信息
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/hadoopdb";
            String username = "root";
            String password = "123456";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);

            //创建语句
            Statement statement = connection.createStatement();
            String sql = "insert into users(name,age) values('tom',123)";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
