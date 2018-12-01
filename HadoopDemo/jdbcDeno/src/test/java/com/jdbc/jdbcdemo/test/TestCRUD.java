package com.jdbc.jdbcdemo.test;

import org.junit.Test;

import java.sql.*;

/*
测试基本操作
 */
public class TestCRUD {
    /*
        语句对象，不能防止sql注入，性能比较低
     */
    @Test
    //time:113220
    public void testStatement() throws Exception {
        Long start = System.currentTimeMillis();
        //创建连接信息
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        connection.setAutoCommit(false);
        //创建语句
        Statement statement = connection.createStatement();
//11575
        for (int i = 0; i < 1000000; i++) {
            String sql = "insert into users(name,age) values('tom" + i + "'," + (i % 100) + ")";
            statement.execute(sql);
        }
        connection.commit();

        statement.close();
        connection.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    /*
    预处理语句
     */
    @Test
    //time 103955
    public void testPreparedStatement() throws Exception {
        Long start = System.currentTimeMillis();

        //创建连接信息
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        connection.setAutoCommit(false);
        //创建语句
        String sql = "insert into users(name,age) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000000; i++) {
            statement.setString(1, "tom" + 1);
            statement.setInt(2, i % 100);
            //将sql保存到批次
            statement.addBatch();
            if (i % 2000 == 0) {
                //统一执行批次（批量提交）
                statement.executeBatch();
            }
        }
        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    /*
    存储过程
     */
    @Test
    //time:20753
    public void testProcedure() throws Exception {
        Long start = System.currentTimeMillis();
        //创建连接信息
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        connection.setAutoCommit(false);
        //创建可调用语句，调用存储过程
        CallableStatement callableStatement = connection.prepareCall("{call sp_batchinsert(?)}");
        callableStatement.setInt(1, 1000000);//绑定参数
        //  callableStatement.setInt(2,3);//绑定参数
        //注册输出类型参数
        //   callableStatement.registerOutParameter(3,Types.INTEGER);
        callableStatement.execute();
        // int i = callableStatement.getInt(3);
        //  System.out.println(i);
        connection.commit();
        callableStatement.close();
        connection.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testFunction() throws Exception {
        Long start = System.currentTimeMillis();
        //创建连接信息
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        connection.setAutoCommit(false);
        //创建可调用语句，调用存储过程
        CallableStatement callableStatement = connection.prepareCall("{? = call sf_add(?,?)}");
        //callableStatement.setInt(1,1000000);//绑定参数
        callableStatement.setInt(2, 100);//
        callableStatement.setInt(3, 200);//绑定参数
        //注册输出类型参数
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        // int i = callableStatement.getInt(3);
        //  System.out.println(i);
        System.out.println(callableStatement.getInt(1));
        connection.commit();
        callableStatement.close();
        connection.close();
        System.out.println(System.currentTimeMillis() - start);
    }

}
