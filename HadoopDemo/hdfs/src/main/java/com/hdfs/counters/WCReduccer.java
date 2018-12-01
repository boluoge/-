package com.hdfs.counters;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WCReduccer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable intWritable : values) {
            count = count + intWritable.get();
        }
        String tno = Thread.currentThread().getName();
        System.out.println(tno + ": WCReduccer:" + key.toString());
        context.getCounter("r", Util.info(this, "reduce")).increment(1);
        context.write(key, new IntWritable(count));
    }
}
