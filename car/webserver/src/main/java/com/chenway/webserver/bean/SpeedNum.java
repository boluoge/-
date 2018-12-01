package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "speed_num")
public class SpeedNum implements Serializable {

    @Id
    @Column(name = "speed")
    private Integer speed;

    @Override
    public String toString() {
        return "SpeedNum{" +
                "speed=" + speed +
                ", num=" + num +
                '}';
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
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
