package com.example.a5_28_mvp_test.base;

import android.app.Application;
import android.content.Context;

public class Aplication1907 extends Application {
    private static Aplication1907 mApplication1907;
    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication1907 = this;
    }

    public Aplication1907 getApplication(){
        return mApplication1907;
    }

    public static Context get07ApplicationContext(){
        return mApplicationContext.getApplicationContext();
    }
}
