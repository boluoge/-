package com.chenway.webserver.dao;

import com.chenway.webserver.bean.CarLog;
import com.chenway.webserver.bean.TFByLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface TFByLocationJPA extends JpaRepository<TFByLocation,String>
        ,JpaSpecificationExecutor<TFByLocation>
        ,Serializable{

//    @Query(value = "select * from topfivebylocation order by num desc ",nativeQuery = true)
//    public List<TFByLocation> findAllOrderByNum1();
}
