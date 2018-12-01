package com.hdfs.counters;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    public WCMapper() {
        System.out.println("new WCMapper");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text word = new Text();
        IntWritable oneWritable = new IntWritable();
        String[] arr = value.toString().split(" ");
        for (String s : arr) {
            word.set(s);
            oneWritable.set(1);
            context.write(word, oneWritable);
            context.getCounter("m", Util.info(this, "map")).increment(1);
        }
    }
}
