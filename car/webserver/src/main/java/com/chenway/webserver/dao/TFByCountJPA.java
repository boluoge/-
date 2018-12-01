package com.chenway.webserver.dao;

import com.chenway.webserver.bean.TFByCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;

public interface TFByCountJPA extends JpaRepository<TFByCount,String>
        ,JpaSpecificationExecutor<TFByCount>
        ,Serializable{


}
