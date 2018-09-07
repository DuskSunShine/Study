package com.hhjt.study.custom_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hhjt.study.R;



public class SafeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_view);
        SafeView safeView=findViewById(R.id.safe);
        TimeView timeView=findViewById(R.id.timeView);
        //safeView.setText("hahahha");
        //safeView.start(10,5000);

    }


}
