package com.hhjt.study;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import me.logg.Logg;

/**
 * 某个类要依赖生命周期操作可以这样处理
 * Created by SCY on 2018/8/22 at 14:26.
 */

public class StudyLifecycle implements LifecycleObserver{


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        Logg.i("onCreate");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Logg.i("onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        Logg.i("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        Logg.i("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Logg.i("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Logg.i("onDestroy");
    }

}
