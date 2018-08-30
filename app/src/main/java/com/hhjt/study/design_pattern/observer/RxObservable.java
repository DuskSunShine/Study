package com.hhjt.study.design_pattern.observer;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Observable;

/**
 * Created by SCY on 2018/8/30 at 10:44.
 * 被观察者对象
 */

public class RxObservable<T> extends Observable {

    private T t;
    private static RxObservable iObservable;
    private static Handler handler;
    public  static RxObservable create() {
        if (iObservable == null) {
            iObservable = new RxObservable();
        }
        if (handler==null){
            handler=new Handler(Looper.getMainLooper());
        }
        Log.v("RxObservable",iObservable.toString());
        return iObservable;
    }

    public RxObservable emitter(@Nullable T t) {
        this.t = t;
        if (t != null) {
            setChanged();
            notifyObservers(t);
        } else {
            Log.w(this.getClass().getSimpleName(), "要更新的数据为null");
        }
        return this;
    }


    public RxObservable postEmitter(@Nullable T t) {
        this.t = t;
        if (t != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    setChanged();
                    notifyObservers(t);
                }
            });
        } else {
            Log.w(this.getClass().getSimpleName(), "要更新的数据为null");
        }
        return this;
    }

    public void subscribe(RxObserver observer) {
        this.addObserver(observer);
    }

    public void unSubscribe(RxObserver observer){
        this.deleteObserver(observer);
    }



}
