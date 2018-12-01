package com.jdbc.jdbcdemo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Test {
    @org.junit.Test
    public void testCRUD() throws Exception {
        Long start = System.currentTimeMillis();
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        //String sql = "insert into users(name,age)values(?,?)";
        // PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        for (int i = 0; i < 1000; i++) {
            String sql = "insert into users(name,age) values('tom" + i + "'," + (i % 100) + ")";
            System.out.println(sql);
            statement.execute(sql);
        }
        connection.commit();
        statement.close();
        connection.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
