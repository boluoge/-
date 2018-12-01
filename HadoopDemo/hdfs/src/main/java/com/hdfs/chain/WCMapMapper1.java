package com.hdfs.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapMapper1 extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyOut = new Text();
        IntWritable one = new IntWritable(1);
        String[] arr = value.toString().split(" ");
        for (String s : arr) {
            keyOut.set(s);
            context.write(keyOut, one);
        }

    }
}
