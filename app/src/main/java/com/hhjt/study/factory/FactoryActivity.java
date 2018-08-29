package com.hhjt.study.factory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hhjt.study.R;

import me.logg.Logg;

public class FactoryActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        textView =findViewById(R.id.textView);

    }

    public void btn1(View view) {
        Iphone iphone = new IFactory.Buidler()
                .setProduceWords("生产 iphone")
                .build().createApple();
        Logg.i(iphone.toString()+"——生产线编号："+iphone.hashCode());
        iphone.produceIphone();
    }

    public void btn2(View view) {
        Android android = new IFactory.Buidler()
                .setProduceWords("生产 android")
                .build().createAndroid();
        Logg.i(android.toString()+"——生产线编号："+android.hashCode());
        android.produceAndroid();

    }

    public void btn3(View view) {
        Computer computer = new IFactory.Buidler()
                .setProduceWords("生产 computer")
                .build().createComputer();
        Logg.i(computer.toString()+"——生产线编号："+computer.hashCode());
        computer.produceComputer();
    }
}
