package com.hhjt.study.javas;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: SCY
 * @CreateDate: 2020/5/11   14:35
 * @Description: 类说明
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/11   14:35
 * @UpdateNote: 更新说明
 * @Version: 对应应用版本
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    String value() default "";

    String name() default "";

    String color() default "";

    int age() default 0;
}
