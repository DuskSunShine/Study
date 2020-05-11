package com.hhjt.study.javas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hhjt.study.R;

import java.lang.reflect.Field;

import me.logg.Logg;

public class AnnotationActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        parseAnnotation(Dog.class);
    }


    public void parseAnnotation(Class<? extends Animal> t){
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
        textView= findViewById(R.id.text);
        textView.setText(dog.toString());
    }
}
