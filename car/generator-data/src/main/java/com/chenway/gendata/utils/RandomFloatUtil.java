package com.chenway.gendata.utils;

import java.util.Random;

public class RandomFloatUtil {

    //取范围内一个浮点数
    public static String nextDouble(final double min, final double max) throws Exception {

        return String.valueOf(min + ((max - min) * new Random().nextDouble()));
    }

}
