package com.chenway.webserver.dao;

import com.chenway.webserver.bean.CarLog;
import com.chenway.webserver.bean.dto.CarLogCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CarLogJPA extends JpaRepository<CarLog,String>
        ,JpaSpecificationExecutor<CarLog>
        ,Serializable{

    @Query(value = "select carid,longitude,latitude,max(time) as time,category,statu,speed,kilometer,location " +
            "from logs group by carid ;",nativeQuery = true)
    public List<CarLog> findLastLocation();


    @Query(value = "select  carid,longitude,latitude,time,category,count(category) as statu,speed,kilometer,location \n" +
            "from " +
            "(select DISTINCT carid,longitude,latitude,time,category,statu,speed,kilometer,location from logs GROUP BY carid)\n" +
            "logs group by category; ",nativeQuery = true)
    public List<CarLog> findCategoryNum();
}
