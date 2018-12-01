package com.hdfs.skew.job2;

import com.hdfs.skew.RandomPartitioner;
import com.hdfs.skew.WCMapperSkew;
import com.hdfs.skew.WCReducerSkew;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/*
 解决数据倾斜问题
 */
public class WCApp2 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCSKEWApp2");        //作业名称
        job.setJarByClass(WCApp2.class); //搜索类
        job.setInputFormatClass(KeyValueTextInputFormat.class);//设置输入格式
        //添加输入路径
        FileInputFormat.addInputPath(job, new Path("e:/data/skew/out/part-r-00000"));
        FileInputFormat.addInputPath(job, new Path("e:/data/skew/out/part-r-00001"));
        FileInputFormat.addInputPath(job, new Path("e:/data/skew/out/part-r-00002"));
        FileInputFormat.addInputPath(job, new Path("e:/data/skew/out/part-r-00003"));

        //设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("e:/data/skew/out2"));

        job.setMapperClass(WCMapperSkew2.class); //设置Mapper类
        job.setReducerClass(WCReducerSkew2.class); //设置Reducer类

        job.setNumReduceTasks(4);           //reducer个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class); //设置格式

        job.setOutputKeyClass(Text.class);  //设置输出格式
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);

    }
}
