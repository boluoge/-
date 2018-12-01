package com.chenway.springbootdruid2.test;

import com.chenway.springbootdruid2.dao.CarLogJPA;
import com.chenway.springbootdruid2.pojo.CarLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private CarLogJPA carLogJPA;

    @org.junit.Test
    public void test(){
        CarLog all = carLogJPA.findById("1").get();
        System.out.println(all.toString());

        //System.out.println(jdbcTemplate1.queryForList("select * from logs").toString());
    }

}
