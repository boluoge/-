package com.chenway.webserver.bean.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarLogCondition {

    @Id
    private String carid;

    private String longitude;

    private String latitude;

    private String newtime;

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

    public String getNewtime() {
        return newtime;
    }

    public void setNewtime(String newtime) {
        this.newtime = newtime;
    }
}
