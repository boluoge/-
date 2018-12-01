package com.chenway.webserver.controller;


import com.chenway.webserver.bean.dto.CarLogFormHbase;
import com.chenway.webserver.service.HbaseService;
import com.google.gson.Gson;
import io.lettuce.core.dynamic.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/car")
public class HbaseController {

@Autowired
private HbaseService hbaseService;
private int i = 1;
private Logger log = LoggerFactory.getLogger(HbaseController.class);

    @RequestMapping(value = "/hbase/getConnection")
    public String getHbaseConnection() throws Exception {
        log.info(hbaseService.getTable("ns1:car").toString());
        return hbaseService.toString();
    }

    @RequestMapping(value = "/hbase/getResultScanner")
    public String getResultScanner(){
//        Map<String, Map<String, String>> resultScanner = hbaseService.getResultScanner("ns1:car");
//        StringBuffer stringBuffer = new StringBuffer();
//        resultScanner.forEach((rowKey,values)->{
//            stringBuffer.append(rowKey);
//        });
//        return stringBuffer.toString();
        String str = hbaseService.getResultScanner("ns1:car");
        System.out.println("hbase-查询所有数据"+str);
        return "";
    }

    @RequestMapping(value = "/hbase/getResultScannerByRowFilter",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getResultScannerByRowFilter(@Param("carid") String carid){
        long oldtime = System.currentTimeMillis();

        int j=1;
        Map<String, Map<String, String>> resultScanner = hbaseService.getResultScannerRowFilter("ns1:car",carid);
        List<CarLogFormHbase> logFormHbases = new ArrayList<>();
        resultScanner.forEach((rowkeys,values)->{
            CarLogFormHbase  carLogFormHbase = new CarLogFormHbase();
            carLogFormHbase.setRowkey(rowkeys);
            values.forEach((rowkey,value)->{
                if(i%2!=0) {
                carLogFormHbase.setLocation(value);
                }else {
                    carLogFormHbase.setSpeed(value);
                }
                i++;
            });
            logFormHbases.add(carLogFormHbase);
        });
        Gson gson = new Gson();
        System.out.println(String.valueOf(System.currentTimeMillis()-oldtime)+":"+gson.toJson(logFormHbases));
        return  gson.toJson(logFormHbases);

    }

}
