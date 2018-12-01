package com.hdfs.chain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCChainApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        Job job = Job.getInstance(conf);

        job.setJobName("WCChainApp");
        job.setJarByClass(WCChainApp.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("e:/data/chain"));
        FileOutputFormat.setOutputPath(job, new Path("e:/data/chain/out"));

        ChainMapper.addMapper(job, WCMapMapper1.class, LongWritable.class, Text.class, Text.class, IntWritable.class, conf);
        ChainMapper.addMapper(job, WCMapMapper2.class, Text.class, IntWritable.class, Text.class, IntWritable.class, conf);
        ChainReducer.setReducer(job, WCReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, conf);
        ChainReducer.addMapper(job, WCReduceMapper1.class, Text.class, IntWritable.class, Text.class, IntWritable.class, conf);
        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }
}
