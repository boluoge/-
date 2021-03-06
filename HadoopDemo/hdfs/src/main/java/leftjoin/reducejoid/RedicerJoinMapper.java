package leftjoin.reducejoid;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RedicerJoinMapper extends Mapper<LongWritable, Text, ComboKey, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //判断是customer还是order
        String line = value.toString();
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        String path = inputSplit.getPath().toString();
        ComboKey comboKey = new ComboKey();
        if (path.contains("customers")) {
            String cid = line.substring(0, line.indexOf(","));
            String cusInfo = line;

            comboKey.setType(0);
            comboKey.setCid(Integer.parseInt(cid));
            comboKey.setCunstomerInfo(cusInfo);
        } else {
            String cid = line.substring(line.lastIndexOf(",") + 1);
            String oid = line.substring(0, line.indexOf(","));
            String oinfo = line.substring(0, line.lastIndexOf(","));

            comboKey.setType(1);
            comboKey.setCid(Integer.parseInt(cid));
            comboKey.setOid(Integer.parseInt(oid));
            comboKey.setCunstomerInfo(oinfo);
        }
        context.write(comboKey, NullWritable.get());
    }
}
