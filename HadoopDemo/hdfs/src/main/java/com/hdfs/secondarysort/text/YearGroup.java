package com.hdfs.secondarysort.text;

import com.hdfs.secondarysort.ComboKey;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class YearGroup extends WritableComparator {
    //必须有这个构造
    protected YearGroup() {
        super(ComboKey.class, true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        ComboKey k1 = (ComboKey) a;
        ComboKey k2 = (ComboKey) b;
        return k1.getYear() - k2.getYear();
    }
}
