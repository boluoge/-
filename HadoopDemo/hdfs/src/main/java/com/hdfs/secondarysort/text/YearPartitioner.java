package com.hdfs.secondarysort.text;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner<ComboKey2, NullWritable> {
    @Override
    public int getPartition(ComboKey2 comboKey2, NullWritable nullWritable, int i) {
        return comboKey2.getYear() % i;
    }
}
