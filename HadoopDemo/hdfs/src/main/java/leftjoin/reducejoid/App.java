package leftjoin.reducejoid;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(configuration);

        job.setJobName("ReduceJoinApp");
        job.setJarByClass(App.class);

        FileInputFormat.addInputPath(job, new Path("e:\\data\\reducejoin"));
        FileOutputFormat.setOutputPath(job, new Path("e:\\data\\reducejoin\\out"));

        job.setMapperClass(RedicerJoinMapper.class);
        job.setReducerClass(ReducerJoinReducer.class);

        job.setMapOutputKeyClass(ComboKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setPartitionerClass(CIDPartitioner.class);
        job.setGroupingComparatorClass(CIDGroupComparator.class);

        job.setSortComparatorClass(ComboKeyComparator.class);

        job.setNumReduceTasks(2);

        job.waitForCompletion(true);
    }
}
