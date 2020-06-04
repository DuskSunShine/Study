package com.hhjt.study.javas;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: SCY
 * @date: 2020/5/12   11:33
 * @desc: view绑定
 * @version: 对应应用版本
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewBind {

    int value();
}
