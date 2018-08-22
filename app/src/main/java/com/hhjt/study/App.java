package com.hhjt.study;

import android.app.Application;

import me.logg.Logg;
import me.logg.config.LoggConfiguration;

/**
 * Created by SCY on 2018/8/22 at 14:33.
 */

public class App extends Application {
    public static final String BASEURL = "http://192.168.2.113:9999";
    @Override
    public void onCreate() {
        super.onCreate();
        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(true)
                .setTag(BuildConfig.APPLICATION_ID)// 自定义全局Tag
                .build();
        Logg.init(configuration);
    }
}
