package com.hhjt.study.mvp.model;

import android.widget.Toast;

import com.hhjt.study.App;
import com.hhjt.study.retrofit.ApiManager;
import com.hhjt.study.retrofit.InitData;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**数据的来源，数据的获取操作
 * Created by SCY on 2018/9/27 at 15:48.
 */

public class Model {

    public static Observable<InitData> getDataFromAPi(){
        return Observable.interval(1, 5, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<InitData>>() {
                    @Override
                    public ObservableSource<InitData> apply(Long aLong) throws Exception {
                        return ApiManager.create().initDevice("007");

                    }
                })
                .onErrorResumeNext(new Observable<InitData>() {
                    @Override
                    protected void subscribeActual(Observer<? super InitData> observer) {
                        observer.onNext(new InitData());
                    }
                }).onErrorReturnItem(new InitData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
