package com.hhjt.study.mvp.presenter;


import android.annotation.SuppressLint;
import android.widget.Toast;

import com.hhjt.study.App;
import com.hhjt.study.mvp.model.Model;
import com.hhjt.study.mvp.view.BaseView;
import com.hhjt.study.retrofit.InitData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by SCY on 2018/9/27 at 15:44.
 */

public class  BasePresenter<V extends BaseView>{

    private V baseView;

    public void  attachView(V baseView){
        this.baseView=baseView;
    }

    public BasePresenter() {
    }
    public void detachView(){
        this.baseView=null;
    }

    public boolean isAttachedView(){
        return this.baseView!=null;
    }


    @SuppressLint("CheckResult")
    public void getDataFromApi(){
        baseView.showLoading();
        Model.getDataFromAPi().subscribe(new Observer<InitData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InitData initData) {
                if (initData.getCode()== null){
                    baseView.showFailueMessage();
                    baseView.onFailue();
                    Toast.makeText(App.getApp(),"失败",Toast.LENGTH_SHORT).show();
                    baseView.cancelLoading();
                    return;
                }
                if (isAttachedView()){
                    baseView.onSuccess();
                    baseView.showData(initData);
                }
            }

            @Override
            public void onError(Throwable e) {
                baseView.showErrorMessage();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
