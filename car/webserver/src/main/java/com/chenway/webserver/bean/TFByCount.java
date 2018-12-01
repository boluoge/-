package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "topfivebycount")
@Entity
public class TFByCount implements Serializable {
    @Id
    @Column(name = "carid")
    private String carid;

    public String getCarid() {
        return carid;
    }

    @Override
    public String toString() {
        return "TFByCount{" +
                "carid='" + carid + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Column(name = "count")
   private Integer count;
}
