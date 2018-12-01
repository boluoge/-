package com.hdfs.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner<IntWritable, IntWritable> {
    @Override
    public int getPartition(IntWritable year, IntWritable temp, int i) {
        int y = year.get() - 1970;
        if (y < 33) {
            return 0;
        } else if (y >= 33 && y < 66) {
            return 1;
        } else {
            return 2;
        }
    }
}
