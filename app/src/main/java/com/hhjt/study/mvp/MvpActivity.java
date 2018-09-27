package com.hhjt.study.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hhjt.study.R;
import com.hhjt.study.mvp.presenter.BasePresenter;
import com.hhjt.study.mvp.view.BaseView;

public class MvpActivity extends AppCompatActivity implements BaseView{
    private BasePresenter<MvpActivity> presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        presenter= new BasePresenter<>();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showData(Object o) {
        presenter.getsData();
    }


    @Override
    public void showErrorMessage() {

    }

    @Override
    public void onFailue() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showFailueMessage() {

    }
}
