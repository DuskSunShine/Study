package com.hhjt.study.factory;

/**
 * Created by SCY on 2018/8/29 at 11:36.
 * 抽象工厂模式
 * 抽象工厂对应的是产品族的概念，如果有多种产品{@link Iphone,Android,Computer}，
 * 则工厂类应该可以创建多种产品的实例{@link IFactory#createAndroid()},
 * {@link IFactory#createApple()},{@link IFactory#createComputer()}。
 * 增加新的一类产品线很方便，但是不好增加新的产品到产品族中。
 * 工厂模式中往往是创建一类商品，而抽象工厂中是同时可以创建多种品类商品。
 * 上帝类，统管所有的生产输出商品。
 */

public interface AbsFactory {
    //工厂对应的生产线，返回值是生产的产品。
    Iphone createApple();
    Android createAndroid();
    Computer createComputer();
}
