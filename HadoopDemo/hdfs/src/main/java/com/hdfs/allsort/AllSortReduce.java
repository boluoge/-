package com.hdfs.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AllSortReduce extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable intWritable : values) {
            max = max > intWritable.get() ? max : intWritable.get();
        }
        context.write(key, new IntWritable(max));
    }
}
