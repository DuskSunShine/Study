package com.hhjt.study;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hhjt.study.IViewModel.StudyViewModel;
import com.hhjt.study.design_pattern.factory.FactoryActivity;
import com.hhjt.study.retrofit.ApiManager;
import com.hhjt.study.retrofit.InitData;
import com.hhjt.study.retrofit.PersonData;
import com.hhjt.study.retrofit.SToast;
import com.hhjt.study.retrofit.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.logg.Logg;

public class MainActivity extends AppCompatActivity {

    private StudyViewModel studyViewModel;
    private TextView text;
    private TextView textView;
    private TextView textView2;
    private int count=0;
    List<User> users;
    Map<String,PersonData.DataBean> persons=new HashMap<>();
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

        updatePerson();
        //mm();
    }

    private void mm() {
        Observable.interval(1,5, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<InitData>>() {
                    @Override
                    public ObservableSource<InitData> apply(Long aLong) throws Exception {
                        return ApiManager.create().initDevice("007");

                    }
                })
                .onErrorResumeNext(new Observable<InitData>() {
                    @Override
                    protected void subscribeActual(io.reactivex.Observer<? super InitData> observer) {
                        observer.onNext(new InitData());
                    }
                })
                .onErrorReturnItem(new InitData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InitData>() {
                    @Override
                    public void accept(InitData initData) throws Exception {
                        if (initData.getCode()== null){
                            Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
                            Logg.e("return");
                            //return;
                        }
                    }
                });
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
    private void updatePerson() {
        Observable.interval(0, 5, TimeUnit.SECONDS)
                .flatMap(aLong -> ApiManager.create().getAllPersons()
                        .onExceptionResumeNext(new Observable<PersonData>() {
                    @Override
                    protected void subscribeActual(io.reactivex.Observer<? super PersonData> observer) {
                        observer.onNext(new PersonData());
                    }
                }).onErrorReturnItem(new PersonData()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<PersonData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonData personData) {
                        if (personData.getCode()==null) {
                            Logg.e("当前人员数量获取失败！");
                            Toast.makeText(MainActivity.this, "当前人员数量获取失败", Toast.LENGTH_SHORT).show();
                            //return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Observable.interval(0, 2, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<InitData>>() {
                    @Override
                    public ObservableSource<InitData> apply(Long aLong) throws Exception {
                        return ApiManager.create().initDevice("007");
                    }
                }).onErrorResumeNext(new Observable<InitData>() {
            @Override
            protected void subscribeActual(io.reactivex.Observer<? super InitData> observer) {
                observer.onNext(new InitData());
            }
        }).onErrorReturnItem(new InitData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<InitData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InitData initData) {
                        if (initData.getCode()==null) {
                            Logg.e("当前init获取失败！");
                            //Toast.makeText(MainActivity.this, "当前init获取失败",Toast.LENGTH_SHORT).show();
                            SToast.toast("当前init获取失败");
                        }
                        Logg.e("当前init获取:"+initData.getCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Logg.e("onError:");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void query(View view) {
        startActivity(new Intent(this, FactoryActivity.class));
    }
}
