package com.hhjt.study.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hhjt.study.retrofit.ApiManager;
import com.hhjt.study.retrofit.InitData;
import com.hhjt.study.retrofit.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
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
        ApiManager.create().initDevice(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InitData>() {
                    @Override
                    public void accept(InitData initData) throws Exception {
                        data.setValue(initData);
                    }
                });
            return data;
    }
    public MutableLiveData<User> getUserById(int id){
        //我们这里假设通过网络请求我们拿到需要的User
        User user=new User(id,"user_name"+id);
        users.setValue(user);
        return users;
    }

    public MutableLiveData<Integer> getUserId(int require) {
        //我们这里假设通过网络请求，我们拿到id=require;
        userId.setValue(require);
        return userId;
    }
}
