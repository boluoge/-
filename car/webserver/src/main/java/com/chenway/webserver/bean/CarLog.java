package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="logs")
public class CarLog
        implements Serializable {
    @Id
    @Column(name = "carid")
    private String carid;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;

    @Override
    public String toString() {
        return "CarLog{" +
                "carid='" + carid + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", time='" + time + '\'' +
                ", category='" + category + '\'' +
                ", statu='" + statu + '\'' +
                ", speed='" + speed + '\'' +
                ", kilometer='" + kilometer + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Column(name = "time")
    private String time;
    @Column(name = "category")
    private String category;
    @Column(name = "statu")
    private String statu;
    @Column(name = "speed")
    private String speed;

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Column(name = "kilometer")
    private String kilometer;
    @Column(name = "location")
    private String location;
}
