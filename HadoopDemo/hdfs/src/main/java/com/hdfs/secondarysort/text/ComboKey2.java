package com.hdfs.secondarysort.text;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComboKey2 implements WritableComparable<ComboKey2> {
    private int year;
    private int temp;

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "ComboKey2{" +
                "year=" + year +
                ", temp=" + temp +
                '}';
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public int compareTo(ComboKey2 o) {
        if (year == o.getYear()) {
            return -(temp - o.getTemp());
        } else
            return year - o.getYear();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(temp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        temp = dataInput.readInt();
    }
}
