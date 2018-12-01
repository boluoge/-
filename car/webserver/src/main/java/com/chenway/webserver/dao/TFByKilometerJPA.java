package com.chenway.webserver.dao;

import com.chenway.webserver.bean.CarLog;
import com.chenway.webserver.bean.TFByKilometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface TFByKilometerJPA extends JpaRepository<TFByKilometer,String>
        ,JpaSpecificationExecutor<TFByKilometer>
        ,Serializable{


}
