package com.jdbc.jdbcdemo.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestIsolationLevel {
    @Test
    public void testA() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute("update users set age=11 where name='tomas'");
        System.out.println("=================");
        // connection.commit();
        statement.close();
        connection.close();
    }

    @Test
    public void testB() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from users where name='tomas'");
        resultSet.next();
        int age = resultSet.getInt("age");
        System.out.println(age);
        System.out.println("=================");
        connection.commit();
        statement.close();
        connection.close();
    }
}
