package com.chenway.springbootjavamail.pojo;

public enum  MailContentTypeEnum {
    HTML("text/html;charest=uft-8"),
    TEXT("text");

    private String value;

    MailContentTypeEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
