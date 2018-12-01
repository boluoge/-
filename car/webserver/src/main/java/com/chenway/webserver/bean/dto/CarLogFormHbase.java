package com.chenway.webserver.bean.dto;

public class CarLogFormHbase {
    private String rowkey;
    private String location;
    private String speed;

    @Override
    public String toString() {
        return "CarLogFormHbase{" +
                "rowkey='" + rowkey + '\'' +
                ", location='" + location + '\'' +
                ", speed='" + speed + '\'' +
                '}';
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
