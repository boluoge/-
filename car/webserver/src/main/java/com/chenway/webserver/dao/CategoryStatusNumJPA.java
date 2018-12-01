package com.chenway.webserver.dao;

import com.chenway.webserver.bean.CategoryStatusNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CategoryStatusNumJPA extends JpaRepository<CategoryStatusNum,String>
        ,JpaSpecificationExecutor<CategoryStatusNum>
        ,Serializable {

}
