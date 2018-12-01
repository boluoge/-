package maxsort.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AllSortMapper extends Mapper<IntWritable, IntWritable, IntWritable, IntWritable> {
    @Override
    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {

        context.write(key, value);
    }
}
