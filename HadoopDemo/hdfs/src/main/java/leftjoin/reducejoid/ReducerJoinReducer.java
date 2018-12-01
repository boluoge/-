package leftjoin.reducejoid;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ReducerJoinReducer extends Reducer<ComboKey, NullWritable, Text, NullWritable> {
    @Override
    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> it = values.iterator();
        it.next();
        int type = key.getType();
        int cid = key.getCid();
        String customerInfo = key.getCunstomerInfo();
        while (it.hasNext()) {
            it.next();
            String oinfo = key.getOrderInfo();
            context.write(new Text(customerInfo + "," + oinfo), NullWritable.get());
        }
    }
}
