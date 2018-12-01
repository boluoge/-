package com.hdfs.secondarysort.text;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<ComboKey2, NullWritable, IntWritable, IntWritable> {
    @Override
    protected void reduce(ComboKey2 key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(new IntWritable(key.getYear()), new IntWritable(key.getTemp()));
    }
}
