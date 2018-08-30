package com.hhjt.study.design_pattern.observer;

/**
 * Created by SCY on 2018/8/30 at 14:49.
 */

public interface Consumer<T> {
    void accept(T value);
}
