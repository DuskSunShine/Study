package com.hhjt.study.design_pattern.factory;

import me.logg.Logg;

/**
 * package:com.hhjt.mkotlin.factory   project: MKotlin
 * Created by SCY on 2018/8/29 at 11:37.
 */

public class Android {
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Android(String words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Android{" +
                "words='" + words + '\'' +
                '}';
    }
    public void produceAndroid(){
        long l = System.currentTimeMillis();
        try {
            Thread.sleep((long) Math.random());
            long l1 = System.currentTimeMillis();
            Logg.i("Android生产完毕，花费时间"+(l1-l));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
