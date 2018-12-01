package com.hdfs.secondarysort.text;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, ComboKey2, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split(" ");
        ComboKey2 comboKey2 = new ComboKey2();
        comboKey2.setYear(Integer.parseInt(arr[0]));
        comboKey2.setTemp(Integer.parseInt(arr[1]));
        context.write(comboKey2, NullWritable.get());
    }
}
