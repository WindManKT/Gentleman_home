package com.wind.Gentleman.utils;

public class WebUtils {

    private WebUtils() {
    }

    public  static boolean isNull(String name){
        return name == null || "".equals(name);
    }
}
