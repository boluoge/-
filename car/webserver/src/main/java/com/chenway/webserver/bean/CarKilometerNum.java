package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "car_kilometer_num")
@Entity
public class CarKilometerNum implements Serializable {

    @Id
    @Column(name = "carnum")
    private Integer carnum;

    @Override
    public String toString() {
        return "CarKilometerNum{" +
                "carnum=" + carnum +
                ", usecarnum=" + usecarnum +
                ", kilometernum=" + kilometernum +
                ", avgkilometer=" + avgkilometer +
                '}';
    }

    @Column(name = "usecarnum")
    private Integer usecarnum;

    public Integer getCarnum() {
        return carnum;
    }

    public void setCarnum(Integer carnum) {
        this.carnum = carnum;
    }

    public Integer getUsecarnum() {
        return usecarnum;
    }

    public void setUsecarnum(Integer usecarnum) {
        this.usecarnum = usecarnum;
    }

    public double getKilometernum() {
        return kilometernum;
    }

    public void setKilometernum(double kilometernum) {
        this.kilometernum = kilometernum;
    }

    public double getAvgkilometer() {
        return avgkilometer;
    }

    public void setAvgkilometer(double avgkilometer) {
        this.avgkilometer = avgkilometer;
    }

    @Column(name = "kilometernum")

    private double kilometernum;

    @Column(name = "avgkilometer")
    private double avgkilometer;
}
