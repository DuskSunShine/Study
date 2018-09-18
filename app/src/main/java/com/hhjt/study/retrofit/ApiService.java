package com.hhjt.study.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by SCY on 2018/8/22 at 15:41.
 */

public interface ApiService {

    @GET("/iwg-welcome/app/device/getCamerasByTermialCode/{did}")
    Observable<InitData> initDevice(@Path("did") String did);

    @FormUrlEncoded
    @POST("/iwg-welcome/app/person/findAllList")
    Observable<PersonData> getAllPersons();

}
