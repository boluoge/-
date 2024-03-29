package com.hdfs.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WCApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        // conf.set("fs.defaultFS","file:///");
        if (args.length > 1) {
            FileSystem.get(conf).delete(new Path(args[1]));
        }
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCApp");        //作业名称
        job.setJarByClass(WCApp.class); //搜索类
        job.setInputFormatClass(TextInputFormat.class);//设置输入格式
        //添加输入路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WCMapper.class); //设置Mapper类
        job.setReducerClass(WCReducer.class); //设置Reducer类

        job.setNumReduceTasks(1);           //reducer个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class); //设置格式

        job.setOutputKeyClass(Text.class);  //设置输出格式
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);

    }
}
