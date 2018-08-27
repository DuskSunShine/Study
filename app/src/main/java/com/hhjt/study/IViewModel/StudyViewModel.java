package com.hhjt.study.IViewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.hhjt.study.App;
import com.hhjt.study.repository.InitDataRepository;
import com.hhjt.study.retrofit.InitData;
import com.hhjt.study.retrofit.User;

import io.reactivex.Observable;

/**
 * 获取数据，不关心数据来源。
 * 相当于大水缸
 * Created by SCY on 2018/8/21 at 17:27.
 */

/**
 *假设我们有一个实体类User类，
 */
public class StudyViewModel extends ViewModel {
    private MutableLiveData<InitData> initData;
    private InitDataRepository initDataRepository = new InitDataRepository();
    private LiveData<User>  userLiveData;
    private MutableLiveData<Integer> userId;


    public LiveData<User> getUserLiveData() {

        userLiveData=Transformations.switchMap(userId,
                new Function<Integer, LiveData<User>>() {
                    @Override
                    public MutableLiveData<User> apply(Integer input) {
                        return initDataRepository.getUserById(input);
                    }
                });
        return userLiveData;
    }

    public MutableLiveData<InitData> getInitData() {
        return initData;
    }
    public void setUserId(int require) {
        userId = initDataRepository.getUserId(require);

    }
    /**
     * 开始获取需要的数据
     */
    public void start() {

        initData = initDataRepository.getInitData("007");

    }

}
