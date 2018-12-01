package maxsort.allsort;

import com.hdfs.allsort.YearPartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.InputSampler;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AllSortAPP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");
        Job job = new Job(configuration);

        job.setJobName("AllSortAPP");
        job.setJarByClass(AllSortAPP.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(AllSortMapper.class);
        job.setReducerClass(AllSortReduce.class);

        job.setNumReduceTasks(3);//必须放到采样前

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        //创建随机采样对象
        //feq：每个key被选中的概率
        //numSamples:抽取样本的总数
        //maxSplitSampled：最大采样切片数
        InputSampler.Sampler<IntWritable, IntWritable> sampler =
                new InputSampler.RandomSampler<IntWritable, IntWritable>(1, 6000, 10);
        //将sample数据写入分区文件
        InputSampler.writePartitionFile(job, sampler);
        //设置全排序分类
        job.setPartitionerClass(TotalOrderPartitioner.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);
    }
}
