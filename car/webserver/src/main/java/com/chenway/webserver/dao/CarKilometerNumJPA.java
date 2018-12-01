package com.chenway.webserver.dao;

import com.chenway.webserver.bean.CarKilometerNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CarKilometerNumJPA extends JpaRepository<CarKilometerNum,Integer>
        ,JpaSpecificationExecutor<CarKilometerNum>
        ,Serializable {
        }
