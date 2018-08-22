package com.hhjt.study.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hhjt.study.retrofit.ApiManager;
import com.hhjt.study.retrofit.InitData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据仓库，提供数据来源
 * Created by SCY on 2018/8/22 at 15:55.
 */

public class InitDataRepository {

    private MutableLiveData<InitData> data=new MutableLiveData<>();

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
}
