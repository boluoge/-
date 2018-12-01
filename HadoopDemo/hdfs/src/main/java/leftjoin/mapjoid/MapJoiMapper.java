package leftjoin.mapjoid;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.ID;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MapJoiMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Map<String, String> allCustomers = new HashMap<String, String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration configuration = context.getConfiguration();
        FileSystem fs = FileSystem.get(configuration);
        FSDataInputStream open = fs.open(new Path("file:///e:/data/join/customers.txt"));
        //得到缓冲区阅读器
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String cid = line.substring(0, line.indexOf(","));
            allCustomers.put(cid, line);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //订单信息
        String line = value.toString();
        //提取cid
        String cid = line.substring(line.lastIndexOf(",") + 1);
        //订单信息
        String orderInfo = line.substring(0, line.lastIndexOf(","));
        //连接customer+“，”+order
        String customerInfo = allCustomers.get(cid);
        context.write(new Text(customerInfo + "," + orderInfo), NullWritable.get());
    }
}
