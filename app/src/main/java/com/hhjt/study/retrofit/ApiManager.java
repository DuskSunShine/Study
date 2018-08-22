package com.hhjt.study.retrofit;

import com.hhjt.study.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SCY on 2018/8/22 at 15:45.
 */

public class ApiManager {
    private static ApiService apiService=null;

    public static ApiService create(){
        if (null == apiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(App.BASEURL)
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
