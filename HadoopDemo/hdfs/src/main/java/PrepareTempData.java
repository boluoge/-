import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import javax.servlet.Servlet;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PrepareTempData {
    //创建seq文件
    @Test
    public void e() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(configuration);
        Path path = new Path("e:/data/temp.seq");
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, configuration, path, IntWritable.class, IntWritable.class);
        for (int i = 0; i < 6000; i++) {
            int year = 1970 + new Random().nextInt(100);
            int temp = -30 + new Random().nextInt(100);
            writer.append(new IntWritable(year), new IntWritable(temp));
        }


        writer.close();
    }
}
