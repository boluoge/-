package com.hdfs.mysql;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WCApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCApp");        //作业名称
        job.setJarByClass(WCApp.class); //搜索类

        //配置数据库信息
        String driverclass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/hadoopdb";
        String username = "root";
        String password = "123456";
        //设置数据库配置
        DBConfiguration.configureDB(job.getConfiguration(), driverclass, url, username, password);
        //设置数据输入内容
        DBInputFormat.setInput(job, MyDBWritable.class, "select id,name,txt from words",
                "select count(*) from words");

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("e:/data/mysql/out"));

        job.setMapperClass(com.hdfs.mysql.WCMapper.class); //设置Mapper类
        job.setReducerClass(com.hdfs.mysql.WCReducer.class); //设置Reducer类

        job.setNumReduceTasks(3);           //reducer个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class); //设置格式

        job.setOutputKeyClass(Text.class);  //设置输出格式
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);

    }
}
