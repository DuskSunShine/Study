package com.hhjt.study.design_pattern.strategy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hhjt.study.R;

import java.nio.channels.SocketChannel;

import me.logg.Logg;

public class StrategyActivity extends AppCompatActivity {
    EditText eprice;
    TextView txt;
    Price price;
    Price price2;
    Price price3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        eprice=findViewById(R.id.price);
        txt=findViewById(R.id.txt);
         price=new Price(AbsPriceStrategy.STRATEGY.H_VIP);
        Logg.i("高级会员："+price.finalPrice(100));
         price2=new Price(AbsPriceStrategy.STRATEGY.L_VIP);
        Logg.i("普通会员："+price2.finalPrice(100));
         price3=new Price(AbsPriceStrategy.STRATEGY.ORIGINAL);
        Logg.i("非会员："+price3.finalPrice(100));
    }

    public void price(View view) {
        String s = eprice.getText().toString();
        String v="高级会员："+price.finalPrice(Double.parseDouble(s))+
                "\n"+"普通会员："+price2.finalPrice(Double.parseDouble(s))
                +"\n"+"非会员："+price3.finalPrice(Double.parseDouble(s));
        txt.setText(v);
    }
}
