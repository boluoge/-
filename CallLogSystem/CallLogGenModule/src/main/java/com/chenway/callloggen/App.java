package com.chenway.callloggen;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
使用maven生成jar包扔到centos上运行
centos>java -cp CallLogGenModule-1.0-SNAPSHOT.jar com.chenway.callloggen.App /home/centos/calllog/calllog.log
 */
public class App {
    private static Random random = new Random();
    public static List<String> phoneNumbers = new ArrayList<String>();
    public static Map<String, String> callers = new HashMap<String, String>();

    static {
        callers.put("15220141678", "陈一");
        callers.put("18046865067", "胡二");
        callers.put("15119884501", "张三 ");
        callers.put("19263361219", "李四");
        callers.put("18032291306", "王五");
        callers.put("12711058869", "任六");
        callers.put("15335593369", "梁七");
        callers.put("12734214050", "刘八");
        callers.put("14618206525", "朱九");
        callers.put("17781348030", "郭十");
        callers.put("18646281129", "邵十一");
        callers.put("15031645440", "黎十二");
        callers.put("13041909505", "肖十三");
        callers.put("13664192665", "杨十四");
        callers.put("18404589132", "骆十五");
        callers.put("13920504953", "郑十六");
        callers.put("18532662075", "黄十七");
        callers.put("18120592721", "罗十八");
        phoneNumbers.addAll(callers.keySet());
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args == null || args.length == 0) {
            System.out.println("no args");
            System.exit(-1);
        }
        genCallLog(args[0]);
//        genCallLog("f:/scala/data/callLog/callLog.log");
    }

    private static void genCallLog(String logFile) throws InterruptedException, IOException {
        //  FileWriter fw = new FileWriter("/home/centos/calllog/calllog.log",true);
        FileWriter fw = new FileWriter(logFile, true);
        while (true) {
            //取主叫
            String caller = phoneNumbers.get(random.nextInt(callers.size()));
            //取主叫名字
            String callerName = callers.get(caller);

            //取被叫
            String callee = null;
            //取被叫名字
            String calleeName = null;
            while (true) {
                callee = phoneNumbers.get(random.nextInt(callers.size()));
                if (!caller.equals(callee)) {
                    break;
                }
            }

            calleeName = callers.get(callee);
            //通话时长
            int duration = random.nextInt(60 * 10) + 1;
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern("000");
            String durStr = decimalFormat.format(duration);

            //通话时间
            int year = 2018;
            int month = random.nextInt(12);
            int day = random.nextInt(29) + 1;
            int hour = random.nextInt(24);
            int min = random.nextInt(60);
            int sec = random.nextInt(60);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, min);
            c.set(Calendar.SECOND, sec);
            Date time = c.getTime();

            Date now = new Date();
            if (time.compareTo(now) > 0) {
                continue;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("yyyy/MM/dd HH:mm:ss");
            String dateStr = simpleDateFormat.format(time);

            //18532662075,黄十七,18404589132,骆十五,2017/06/26 17:32:35,460
            //String log = caller+","+callerName+","+callee+","+calleeName+","+dateStr+","+durStr;

            //13041909505,,17781348030,,2017/09/02 11:47:02,225
            String log = caller + "," + callee + "," + dateStr + "," + durStr;
            System.out.println(log);
            fw.write(log + "\r\n");
            fw.flush();
            Thread.sleep(200);
        }
    }
}
