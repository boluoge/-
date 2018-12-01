package com.chenway.webserver.dao;

import com.chenway.webserver.bean.SpeedNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface SpeedNumJPA extends JpaRepository<SpeedNum,String>
        ,JpaSpecificationExecutor<SpeedNum>
        ,Serializable {
}
