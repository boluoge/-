package com.hdfs.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AllSortMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arrs = value.toString().split(" ");
        context.write(new IntWritable(Integer.parseInt(arrs[0])), new IntWritable(Integer.parseInt(arrs[1])));
    }
}
