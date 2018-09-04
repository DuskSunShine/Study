package com.hhjt.study.design_pattern.factory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.hhjt.study.R;
import com.hhjt.study.design_pattern.observer.Consumer;
import com.hhjt.study.design_pattern.observer.RxObservable;
import com.hhjt.study.design_pattern.observer.RxObserver;
import com.hhjt.study.design_pattern.strategy.StrategyActivity;

import java.util.EnumSet;

import me.logg.Logg;

public class FactoryActivity extends AppCompatActivity {
    TextView textView;
    RxObserver rxObserver;
    private RxObservable iObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        textView =findViewById(R.id.textView);

        /**
         * 观察者模式
         */
        iObservable = RxObservable.create();
        rxObserver =new RxObserver(new Consumer() {
            @Override
            public void accept(Object value) {
                Logg.e("accept:"+value.toString());
            }
        });
        iObservable.subscribe(rxObserver);
    }

    /**
     * 构建者模式
     * @param view
     */
    public void btn1(View view) {
        Iphone iphone = new IFactory.Buidler()
                .setProduceWords("生产 iphone")
                .build().createApple();
        Logg.i(iphone.toString()+"——生产线编号："+iphone.hashCode());
        iphone.produceIphone();
        iObservable.emitter(iphone);
    }

    public void btn2(View view) {
        Android android = new IFactory.Buidler()
                .setProduceWords("生产 android")
                .build().createAndroid();
        Logg.i(android.toString()+"——生产线编号："+android.hashCode());
        android.produceAndroid();
        iObservable.emitter(android);
    }

    public void btn3(View view) {
        Computer computer = new IFactory.Buidler()
                .setProduceWords("生产 computer")
                .build().createComputer();
        Logg.i(computer.toString()+"——生产线编号："+computer.hashCode());
        computer.produceComputer();
       iObservable.emitter(computer);
    }

    public void str(View view) {
        startActivity(new Intent(this, StrategyActivity.class));
    }
}
