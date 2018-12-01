package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "topfivebyspeed")
@Entity
public class TFBySpeed implements Serializable {//

    @Override
    public String toString() {
        return "TFBySpeed{" +
                "speed='" + speed + '\'' +
                ", carid='" + carid + '\'' +
                '}';
    }

    @Column(name = "speed")
    private Integer speed;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    @Column(name = "carid")
    @Id
    private String carid;

}
