package com.chenway.webserver.bean;

import java.io.Serializable;

public class CategoryStatusID implements Serializable {
    private String category;

    @Override
    public String toString() {
        return "CategoryStatusID{" +
                "category='" + category + '\'' +
                ", statu='" + statu + '\'' +
                '}';
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

    private String statu;
}
