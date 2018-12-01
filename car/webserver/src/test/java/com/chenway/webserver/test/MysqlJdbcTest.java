package com.chenway.webserver.test;

import com.chenway.webserver.bean.CarLog;
import com.chenway.webserver.bean.dto.CarLogCondition;
import com.chenway.webserver.dao.CarLogJPA;
import com.chenway.webserver.dao.CategoryStatusNumJPA;
import com.chenway.webserver.dao.HbaseDao;
import com.chenway.webserver.dao.TFByLocationJPA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlJdbcTest {

   @Autowired
    TFByLocationJPA tfByLocationJPA;
   @Autowired
   CarLogJPA carLogJPA;
   @Autowired
    CategoryStatusNumJPA categoryStatusNumJPA;
   @Autowired
    HbaseDao hbaseDao;
    @Test
    public void test(){
//
//
//        List<CarLog> carLogsDistinct = carLogJPA.findCategoryNum();
//        for(CarLog carLog:carLogsDistinct){
//            System.out.println(carLog);
//        }
        long oldtime = System.currentTimeMillis();

//        Map<String, Map<String, String>> result = hbaseDao.getResultScannerRowFilter("ns1:car", "粤B6T151");
//        Map<String, Map<String, String>> result = hbaseDao.getResultScannerPrefixFilter("ns1:car", "65");
//        65,粤B6T151,23.264600200332247,113.25664021818453,20181002024711,客运车,0,00253
//        65,粤B6T151,23.50679098884462,113.51427272748947,20181002024832,客运车,1,03431
//        65,粤B6T151,23.37064212491184,113.48081929159756,20181002024833,客运车,1,03514
//        Map<String, Map<String, String>> result = hbaseDao.getResultScanner("ns1:car");
//        result.forEach((rowkeys,values)->{
//            System.out.println(rowkeys+values);
//        });
//        System.out.println("result.size()"+result.size());
//        System.out.println(System.currentTimeMillis()-oldtime);
    }
}
