package com.hive.udf;

import com.sun.org.glassfish.gmbal.Description;

@Description(
        name = "myadd",
        value = "add(int a,int b)==>return a+b",
        extended = "eg:\n"
                + "add(1,2)==>2 \n"
                + "add(1,2,3)==>6;"
)
public class AddUDF extends UDF {
    public int evaluate(int a, int b) {
        return a + b;

    }

    public int evaluate(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {

    }
}
