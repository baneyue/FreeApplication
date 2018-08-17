package com.base.demo.di;

import android.util.Log;

import com.base.demo.mvp.model.service.CASService;
import com.base.utils.HttpSSLUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static String TAG = AppModule.class.getSimpleName();

    private Retrofit retrofitCAS;

    private OkHttpClient okHttpClient;

    @Provides
    Retrofit provideRetrofitCAS() {
        Log.d(TAG, "provideRetrofitCAS()");
        if (okHttpClient == null) {
            Log.d(TAG, "need init okhttp");
            provideOkHttpClient();
        }
        retrofitCAS = new Retrofit.Builder()
                .baseUrl("https://cas.hailuo1688.com")
//                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //适配RxJava2.0, RxJava1.x则为RxJavaCallAdapterFactory.create()
                .client(okHttpClient)
                .build();
        return retrofitCAS;
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        Log.d(TAG, "provideOkHttpClient()");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("AppModule", message);
            }
        });
//        if (ApplicationProxy.isDebug())
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        else
//            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        //无证书校验，全信任
        HttpSSLUtils.SSLParams sslParams = HttpSSLUtils.getSslSocketFactory(null, null, null);
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addNetworkInterceptor(interceptor)
//                .addNetworkInterceptor(new GzipRequestInterceptor())
                .build();
        return okHttpClient;
    }

    @Provides
    CASService provideCASService() {
        if (retrofitCAS == null) {
            Log.d(TAG, "need init RetrofitCAS");
            provideRetrofitCAS();
        }
        return retrofitCAS.create(CASService.class);
    }
}
