package com.hhjt.study.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.hhjt.study.App;
import com.hhjt.study.retrofit.ApiManager;
import com.hhjt.study.retrofit.InitData;
import com.hhjt.study.retrofit.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据仓库，提供数据来源。
 * 相当于水管。
 * Created by SCY on 2018/8/22 at 15:55.
 */

public class InitDataRepository {

    private MutableLiveData<InitData> data=new MutableLiveData<>();
    private MutableLiveData<User> users=new MutableLiveData<>();
    private MutableLiveData<Integer> userId=new MutableLiveData<>();

    public MutableLiveData<InitData> getInitData(String deviceId){
        Observable.interval(1,5, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<InitData>>() {
                    @Override
                    public ObservableSource<InitData> apply(Long aLong) throws Exception {
                        return ApiManager.create().initDevice(deviceId);

                    }
                })
                .onErrorResumeNext(new Observable<InitData>() {
                    @Override
                    protected void subscribeActual(Observer<? super InitData> observer) {
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
                            Toast.makeText(App.getApp(),"失败",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        data.setValue(initData);
                    }
                });

            return data;
    }
    public MutableLiveData<User> getUserById(int id){
        //我们这里假设通过网络请求我们拿到需要的User
        User user=new User(id,"user_name"+id);
        users.setValue(user);
        App.getApp().getDatabase().userDao().save(user);
        return users;
    }

    public MutableLiveData<Integer> getUserId(int require) {
        //我们这里假设通过网络请求，我们拿到id=require;
        userId.setValue(require);
        return userId;
    }
}
