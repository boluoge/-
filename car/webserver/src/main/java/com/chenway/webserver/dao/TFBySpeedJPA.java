package com.chenway.webserver.dao;

import com.chenway.webserver.bean.TFBySpeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface TFBySpeedJPA extends JpaRepository<TFBySpeed,String>
        ,JpaSpecificationExecutor<TFBySpeed>
        ,Serializable{


}
