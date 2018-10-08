package com.hhjt.study;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.text.TextUtils;

import com.hhjt.study.room_orm.IDatabase;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        CrashReport.initCrashReport(this,"0482904ade",false);
    }

    public IDatabase getDatabase() {
        return database;
    }

    public static App getApp() {
        return app;
    }

    private void initBugly() {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "0482904ade", false, strategy);
    }

    
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
