package com.yuguo.business.utils;

import android.app.Application;
/**
 * 程序上下文
 * @author c
 * 
 **/
public class ContextUtil extends Application {
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        return instance;
    }

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
