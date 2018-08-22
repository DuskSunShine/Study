package com.hhjt.study.IViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hhjt.study.repository.InitDataRepository;
import com.hhjt.study.retrofit.InitData;

/**
 * 获取数据，不关心数据来源
 * Created by SCY on 2018/8/21 at 17:27.
 */

public class StudyViewModel extends ViewModel {
    private MutableLiveData<InitData> initData;
    private InitDataRepository initDataRepository = new InitDataRepository();

    public MutableLiveData<InitData> getInitData() {
        return initData;
    }

    /**
     * 开始加载需要的数据
     */
    public void start() {

        initData = initDataRepository.getInitData("007");

    }

}
