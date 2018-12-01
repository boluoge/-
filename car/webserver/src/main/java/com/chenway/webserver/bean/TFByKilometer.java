package com.chenway.webserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "topfivebykilometer")
@Entity
public class TFByKilometer implements Serializable {
    public Integer getKilometer() {
        return kilometer;
    }

    @Override
    public String toString() {
        return "TFByKilometer{" +
                "kilometer='" + kilometer + '\'' +
                ", carid='" + carid + '\'' +
                '}';
    }

    public void setKilometer(Integer kilometer) {
        this.kilometer = kilometer;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    @Column(name = "kilometer")
    private Integer kilometer;
    @Id
    @Column(name = "carid")
    private String carid;
}
