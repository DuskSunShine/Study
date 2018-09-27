package com.hhjt.study.mvp.presenter;


import android.annotation.SuppressLint;
import android.widget.Toast;

import com.hhjt.study.App;
import com.hhjt.study.mvp.model.Model;
import com.hhjt.study.mvp.view.BaseView;
import com.hhjt.study.retrofit.InitData;

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
    public void getsData(){
        baseView.showLoading();
        Model.getdata().subscribe(new Consumer<InitData>() {
            @Override
            public void accept(InitData initData) throws Exception {
                if (initData.getCode()== null){
                    Toast.makeText(App.getApp(),"失败",Toast.LENGTH_SHORT).show();
                    baseView.cancelLoading();
                    return;
                }
                if (isAttachedView()){
                    baseView.showData(initData);
                }
            }
        });
    }
}
