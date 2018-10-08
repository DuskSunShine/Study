package com.hhjt.study.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hhjt.study.R;
import com.hhjt.study.mvp.presenter.BasePresenter;
import com.hhjt.study.mvp.view.BaseView;
import com.hhjt.study.retrofit.InitData;

import me.logg.Logg;

public class MvpActivity extends AppCompatActivity implements BaseView{
    private BasePresenter<MvpActivity> presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        presenter= new BasePresenter<>();
        presenter.attachView(this);
        presenter.getDataFromApi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {
        Logg.e("showLoading");
    }

    @Override
    public void cancelLoading() {
        Logg.e("cancelLoading");
    }

    @Override
    public void showData(InitData initData) {
        Logg.e("showData:"+initData.getData().get(0).toString());
    }


    @Override
    public void showErrorMessage() {
        Logg.e("showErrorMessage");
    }

    @Override
    public void onFailue() {
        Logg.e("onFailue");
    }

    @Override
    public void onSuccess() {
        Logg.e("onSuccess");
    }

    @Override
    public void showFailueMessage() {
        Logg.e("showFailueMessage");
    }
}
