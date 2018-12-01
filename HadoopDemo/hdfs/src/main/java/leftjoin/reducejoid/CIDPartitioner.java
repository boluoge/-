package leftjoin.reducejoid;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/*
    自定义分区类，按照cid分区
 */
public class CIDPartitioner extends Partitioner<ComboKey, NullWritable> {

    @Override
    public int getPartition(ComboKey comboKey, NullWritable nullWritable, int i) {
        return comboKey.getCid() % i;
    }
}
