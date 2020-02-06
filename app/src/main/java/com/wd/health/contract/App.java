package com.wd.health.contract;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
