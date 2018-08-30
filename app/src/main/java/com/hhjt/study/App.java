package com.hhjt.study;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.hhjt.study.room_orm.IDatabase;

import me.logg.Logg;
import me.logg.config.LoggConfiguration;

/**
 * Created by SCY on 2018/8/22 at 14:33.
 */

public class App extends Application {
    public static final String BASEURL = "http://192.168.2.113:9999";
    private IDatabase database;
    private static  App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
         database = Room.databaseBuilder(this, IDatabase.class, "study")
                 .allowMainThreadQueries()
                 .build();


        LoggConfiguration configuration = new LoggConfiguration.Buidler()
                .setDebug(true)
                .setTag(BuildConfig.APPLICATION_ID)// 自定义全局Tag
                .build();
        Logg.init(configuration);
    }

    public IDatabase getDatabase() {
        return database;
    }

    public static App getApp() {
        return app;
    }
}
