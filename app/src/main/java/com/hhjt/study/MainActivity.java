package com.hhjt.study;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hhjt.study.IViewModel.StudyViewModel;
import com.hhjt.study.retrofit.InitData;

public class MainActivity extends AppCompatActivity {

    private StudyViewModel studyViewModel;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);

        studyViewModel = ViewModelProviders.of(this)
                .get(StudyViewModel.class);
        studyViewModel.start();
        /**
         * 观察数据的变化，及时更新
         */
        studyViewModel.getInitData().observe(this, new Observer<InitData>() {
            @Override
            public void onChanged(@Nullable InitData data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    text.setText(data.getData().get(i).toString());
                }
            }
        });

        getLifecycle().addObserver(new StudyLifecycle());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
