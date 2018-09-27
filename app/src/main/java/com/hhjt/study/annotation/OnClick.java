package com.hhjt.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by SCY on 2018/9/27 at 14:18.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface OnClick {
    int [] value();
}
