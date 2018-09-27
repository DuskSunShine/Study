package com.hhjt.study.mvp.view;

import com.hhjt.study.retrofit.InitData;

/**
 * Created by SCY on 2018/9/27 at 15:41.
 */

public interface BaseView<D> {
    void showLoading();
    void cancelLoading();
    void showData(D d);
    void showErrorMessage();
    void onFailue();
    void onSuccess();
    void showFailueMessage();
}
