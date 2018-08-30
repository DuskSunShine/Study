package com.hhjt.study;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hhjt.study.IViewModel.StudyViewModel;
import com.hhjt.study.design_pattern.factory.FactoryActivity;
import com.hhjt.study.retrofit.InitData;
import com.hhjt.study.retrofit.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.logg.Logg;

public class MainActivity extends AppCompatActivity {

    private StudyViewModel studyViewModel;
    private TextView text;
    private TextView textView;
    private TextView textView2;
    private int count=0;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);

        studyViewModel = ViewModelProviders.of(this)
                .get(StudyViewModel.class);
        studyViewModel.start();

        App.getApp().getDatabase().userDao().getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users!=null&&!users.isEmpty()) {
                    MainActivity.this.users=users;
                    Observable.fromIterable(users)
                            .forEach(new Consumer<User>() {
                                @Override
                                public void accept(User user) throws Exception {
                                    Logg.i(user.toString());

                                }
                            });
                    textView.setText(users.get(users.size()-1).toString());
                }else {
                    textView.setText("空");
                }

            }
        });
        App.getApp().getDatabase().userDao().load("10").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    textView2.setText(user.toString());
                }else {
                    textView2.setText("空");
                }
            }
        });

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

        studyViewModel.setUserId(count);
        studyViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                text.setText(user.toString());
            }
        });

        /**
         *
         */
        getLifecycle().addObserver(new StudyLifecycle());
    }
    public void click(View view) {
        count++;
        studyViewModel.setUserId(count);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (users!=null) {
            Logg.i("db删除");
            App.getApp().getDatabase().userDao().delete(users);
        }else {
            Logg.i("db删除失败");
        }
    }

    public void query(View view) {
        startActivity(new Intent(this, FactoryActivity.class));
    }
}
