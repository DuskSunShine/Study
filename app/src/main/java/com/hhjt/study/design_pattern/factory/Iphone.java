package com.hhjt.study.design_pattern.factory;

import me.logg.Logg;

/**
 * Created by SCY on 2018/8/29 at 11:37.
 */

public class Iphone {
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Iphone(String words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Iphone{" +
                "words='" + words + '\'' +
                '}';
    }
    //生产产品具体的流程
    public void produceIphone(){
        long l = System.currentTimeMillis();
        try {
            Thread.sleep((long) Math.random());
            long l1 = System.currentTimeMillis();
            Logg.i("Iphone生产完毕，花费时间"+(l1-l));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
