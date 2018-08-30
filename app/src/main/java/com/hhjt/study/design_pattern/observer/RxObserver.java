package com.hhjt.study.design_pattern.observer;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by SCY on 2018/8/30 at 10:49.
 * 观察者对象
 */

public class RxObserver<T> implements Observer{
    private Consumer consumer;

    public RxObserver(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void update(Observable o, Object arg) {
        T t = (T) arg;

        if (consumer!=null){
            consumer.accept(t);
        }
    }

}
