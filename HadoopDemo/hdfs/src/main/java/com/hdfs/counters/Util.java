package com.hdfs.counters;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Util {

    public static String info(Object o, String msg) {
        return getHostname() + ":" + getPID() + ":" + getTID() + ":" + getObjInfo(o) + ":" + msg;
    }

    /*
    得到主机名
     */
    public static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
/*
获得当前程序所在进程ID
 */

    public static int getPID() {
        try {

            String info = ManagementFactory.getRuntimeMXBean().getName();//pid@hostname
            return Integer.valueOf(info.substring(0, info.indexOf("@")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     获得当前线程ID
     */
    public static String getTID() {
        try {
            return Thread.currentThread().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getObjInfo(Object o) {
        try {
            String sname = o.getClass().getSimpleName();
            return sname + "@" + o.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
