package com.hhjt.study.custom_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hhjt.study.R;

public class SafeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_view);
        SafeView safeView=findViewById(R.id.safe);
        //safeView.setText("hahahha");
        safeView.start(2,5000);
    }
}

