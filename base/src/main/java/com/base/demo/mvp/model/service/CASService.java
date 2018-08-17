package com.base.demo.mvp.model.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CASService {
    @FormUrlEncoded
    @POST("/CasManager/index/login")
    Observable<ResponseBody> login(@Field("username") String username, @Field("password") String password,
                                   @Field("application") String application, @Field("appType") String appType,
                                   @Field("channel") String channel, @Field("appCate") String appCate);
}
