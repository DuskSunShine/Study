package com.hhjt.study.javas;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hhjt.study.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.logg.Logg;

/**
 * 注解和反射学习
 */
public class AnnotationActivity extends AppCompatActivity {

    @ViewBind(R.id.a_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindView(this);
        parseAnnotation(Dog.class);
    }


    /**
     * 手写APT,获取注解的值
     * @param t
     */
    public void parseAnnotation(Class<? extends Animal> t){
        /**
         * 注解是否存在
         */
        boolean annotationPresent = t.isAnnotationPresent(Property.class);
        if (!annotationPresent) {
            return;
        }
        Property annotation = t.getAnnotation(Property.class);

        int age = annotation.age();
        String name = annotation.name();
        String value = annotation.value();
        Dog dog = new Dog();
        dog.setAge(age);
        dog.setSex(value);
        dog.setName(name);
        Field[] declaredFields = t.getDeclaredFields();
        for (Field f:declaredFields) {
            f.setAccessible(true);
            Logg.e("dog:"+f.getName());
            if (f.getName().equals("color")) {
                Property annotation1 = f.getAnnotation(Property.class);
                dog.setColor(annotation1.color());
            }
        }
        Logg.e("dog:"+dog.toString());
//        textView= findViewById(R.id.a_text);
        textView.setText(dog.toString());
    }


    /**
     * 手写View的绑定
     * @param activity
     */
    public void bindView(Activity activity){
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f:declaredFields) {
            if (f.isAnnotationPresent(ViewBind.class)){
                ViewBind annotation = f.getAnnotation(ViewBind.class);
                try {
                    Method findViewById = aClass.getMethod("findViewById", int.class);
                    findViewById.setAccessible(true);
                    Object invoke = findViewById.invoke(activity, annotation.value());
                    f.set(activity,invoke);
                    Logg.e("dog--:"+annotation.value()+",,"+findViewById.getName());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
