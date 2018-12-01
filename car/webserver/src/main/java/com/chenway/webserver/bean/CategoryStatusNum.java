package com.chenway.webserver.bean;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "category_statu_num")
@Entity
@IdClass(CategoryStatusID.class)
public class CategoryStatusNum implements Serializable {
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryStatusNum{" +
                "category='" + category + '\'' +
                ", statu='" + statu + '\'' +
                ", num=" + num +
                '}';
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Id
    @Column(name = "category")
    private String category;

    @Id
    @Column(name = "statu")
    private String statu;

    @Column(name = "num")
    private Integer num;
}
