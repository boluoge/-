package com.chenway.springbootdruid2.dao;

import com.chenway.springbootdruid2.pojo.CarLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CarLogJPA extends JpaRepository<CarLog,String>
        ,JpaSpecificationExecutor<CarLog>
        ,Serializable{

}
