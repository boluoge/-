package com.hdfs.mysql;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<LongWritable, MyDBWritable, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, MyDBWritable value, Context context) throws IOException, InterruptedException {
        String line = value.getTxt();
        String arr[] = line.split(" ");
        System.out.println(value.getTxt());
        for (String s : arr) {
            context.write(new Text(s), new IntWritable(1));
        }
    }
}
