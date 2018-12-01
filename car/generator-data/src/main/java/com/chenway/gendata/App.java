package com.chenway.gendata;

import com.chenway.common.utils.PropertiesUtil;
import com.chenway.gendata.utils.GetLocationUtil;
import com.chenway.gendata.utils.RandomFloatUtil;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
使用maven生成jar包扔到centos上运行
centos>java -cp CallLogGenModule-1.0-SNAPSHOT.jar com.chenway.callloggen.App /home/centos/calllog/calllog.log
 */
public class App {
    private static Random random = new Random();
    public  static Map<String, String> cars = new HashMap<String, String>();
    private static  DecimalFormat decimalFormat = new DecimalFormat();
    public static List<String> carsName = new ArrayList<String>();

//    static {
//        cars.put("粤B02524", "陈一");
//        cars.put("京A70453", "胡二");
//        cars.put("闽D234H1", "张三 ");
//        cars.put("粤A03544", "李四");
//        cars.put("粤A45144", "王五");
//        cars.put("粤AF67T3", "任六");
//        cars.put("粤A15G37", "梁七");
//        cars.put("粤A31240", "刘八");
//        cars.put("粤AJG127", "朱九");
//        cars.put("粤A23870", "郭十");
//        cars.put("粤AI1871", "邵十一");
//        cars.put("粤A512GJ", "黎十二");
//        cars.put("粤A1E90D", "肖十三");
//        cars.put("粤A15471", "杨十四");
//        cars.put("粤B1331G", "骆十五");
//        cars.put("粤B531SZ", "郑十六");
//        cars.put("粤B2131X", "黄十七");
//        cars.put("粤B6T151", "罗十八");
//        cars.put("粤B13221", "叶十九");
//        cars.put("粤B1310R", "詹二十");
//        carsName.addAll(cars.keySet());
//    }
    static {
        cars.put("粤B02524", "客运车");
        cars.put("京A70453", "危险品车");
        cars.put("闽D234H1", "网约车");
        cars.put("粤A03544", "客运车");
        cars.put("粤A45144", "危险品车");
        cars.put("粤AF67T3", "网约车");
        cars.put("粤A15G37", "客运车");
        cars.put("粤A31240", "危险品车");
        cars.put("粤AJG127", "学生校车");
        cars.put("粤A23870", "客运车");
        cars.put("粤AI1871", "网约车");
        cars.put("粤A512GJ", "网约车");
        cars.put("粤A1E90D", "网约车");
        cars.put("粤A15471", "客运车");
        cars.put("粤B1331G", "网约车");
        cars.put("粤B531SZ", "危险品车");
        cars.put("粤B2131X", "网约车");
        cars.put("粤B6T151", "客运车");
        cars.put("粤B13221", "网约车");
        cars.put("粤B1310R", "学生校车");
        carsName.addAll(cars.keySet());

    }

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.println("no args");
            System.exit(-1);
        }
        genCallLog(args[0]);
//        try {
//            genCallLog("f:/scala/data/callLog/callLog.log");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void genCallLog(String logFile) throws Exception {
//          FileWriter fw = new FileWriter("/home/centos/calllog/calllog.log",true);
        FileWriter fw = new FileWriter(logFile, true);
        while (true) {
            //取最大经纬度
            String[] max_longitude_and_atitudes = PropertiesUtil.getString("max_longitude_and_atitude").split(",");
            //取最小经纬度
            String[] min_longitude_and_atitudes = PropertiesUtil.getString("min_longitude_and_atitude").split(",");
            //取车牌号
            String car_Name = carsName.get(random.nextInt(cars.size()));
            //车类别
            String car_category = cars.get(car_Name);
            //取经度
            String car_longitude = RandomFloatUtil.nextDouble(
                    Double.parseDouble(min_longitude_and_atitudes[0]),
                    Double.parseDouble(max_longitude_and_atitudes[0]));
            //取纬度
            String  car_atitude = RandomFloatUtil.nextDouble(
                    Double.parseDouble(min_longitude_and_atitudes[1]),
                    Double.parseDouble(max_longitude_and_atitudes[1]));

            //时间
            Calendar c = Calendar.getInstance();

            Date time = c.getTime();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern(PropertiesUtil.getString("car_drive_time_pattern"));
            String car_dateStr = simpleDateFormat.format(time);//2018/09/25 13:41:00

            //状态
            String car_statu = String.valueOf(random.nextInt(PropertiesUtil.getInt("car_drive_max_status")));

            //时速
            int max_speed = random.nextInt(PropertiesUtil.getInt("car_drive_max_speed"));
            decimalFormat.applyPattern(PropertiesUtil.getString("car_drive_speed_pattern"));
            String car_speed = decimalFormat.format(max_speed);
            if(car_statu.equals("1")||car_statu.equals("2")){
                car_speed ="0";
            }

            //里程数
            int max_kilometre = random.nextInt(PropertiesUtil.getInt("car_drive_max_kilometres"));
            decimalFormat.applyPattern(PropertiesUtil.getString("car_drive_kilometres_pattern"));
            String car_kilometre = decimalFormat.format(max_kilometre);

            //位置
            String car_location=GetLocationUtil.getLocation(car_atitude,car_longitude).toString();

            String log = car_Name + "," + car_longitude + "," + car_atitude + ","
                    + car_dateStr+","+car_category+","+car_statu+","+car_speed+","+car_kilometre+","
                    +car_location;
            System.out.println(log);
            fw.write(log + "\n");
            fw.flush();
            Thread.sleep(PropertiesUtil.getInt("thread_sleep"));
        }
    }
}
