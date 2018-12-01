package com.chenway.kafkaconsumer.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestHdfs {

    @Test
    public void testDate() throws ParseException {
        /*
        dd/MM/YYYY:HH:mm:ss  18/07/2018:20:30:19
        DD/MM/YYYY:HH:mm:ss  199/07/2018:20:30:48
        dd/mm/YYYY:HH:mm:ss  18/31/2018:20:31:09
        dd/MM/yyyy:HH:mm:ss   18/07/2018:20:31:32
        dd/MM/YYYY:hh:mm:ss   18/07/2018:08:31:53
        dd/MM/YYYY:HH:MM:ss   18/07/2018:20:07:18
        dd/MM/YYYY:HH:mm:SS   18/07/2018:20:32:278
        dd/MMM/YYYY:HH:mm:ss  18/Jul/2018:20:33:17
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/YYYY:HH:mm:ss", Locale.US);
//        Date date = new Date();
//        System.out.println(sdf.format(date));

        Date d = sdf.parse("18/Jul/2018:20:33:17");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm", Locale.US);
        System.out.println(dateFormat.format(d));
    }

    @Test
    public void formatYyyyMmDdHhMi() {
        String log = "s129|||192.168.23.1|||-|||18/Jul/2018:09:04:43 -0400|||GET /eshop/phone/mi.html HTTP/1.0|||200|||213|||-|||ApacheBench/2.3|||-";
        String[] arr = log.split("\\|\\|\\|");
        System.out.println(arr.length);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
            Date d = sdf.parse(arr[3].split(" ")[0]);
            System.out.println(d);
            SimpleDateFormat localSdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm", Locale.US);
            System.out.println(localSdf.format(d));
            // return localSdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //  return null;
    }


}
