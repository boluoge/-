package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "topfivebylocation")
public class TFByLocation  implements Serializable { //
    @Id
    @Column(name = "location")
    private String location;

    @Override
    public String toString() {
        return "TFByLocation{" +
                "location='" + location + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Column(name = "num")
    private Integer num;
}
