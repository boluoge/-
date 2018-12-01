package com.chenway.webserver.utlis;

public class StringUtils {


    public static boolean strIsNotNull(String str){
        if(str!=null && str!=""){
            return true;
        }
        return false;
    }



    public static boolean objectIsInRedis(Object object){
        if(object!=null){
            return true;
        }
        return false;
    }
}
