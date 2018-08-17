package com.base.demo.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.base.utils.TagUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * ================================================
 * 提供一些三方库客户端实例的 {@link Module}
 * <p>
 * Created by bond.zou on 2018/8/17.
 * ================================================
 */
@Module
public abstract class HttpClientModule {
    private static final String TAG = TagUtils.makeLogTag(HttpClientModule.class);
    private static final int CONNECT_TIME_OUT = 15;
    private static final int READ_TIME_OUT = 15;

    /**
     * 提供 {@link Retrofit}
     *
     * @param configuration
     * @param builder
     * @param client
     * @param httpUrl
     * @param gson
     * @return {@link Retrofit}
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofit(@Nullable RetrofitConfiguration configuration, Retrofit.Builder builder, OkHttpClient client
            , HttpUrl httpUrl, Gson gson) {
        Timber.tag(TAG).d("provideRetrofit()");

        builder.baseUrl(httpUrl)// 域名
                .client(client);// 设置okhttp

        if (configuration != null) {
            // 交给外部扩展
            configuration.configRetrofit(builder);
        }

        return builder.addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //适配RxJava2.0, RxJava1.x则为RxJavaCallAdapterFactory.create()
                .build();
    }

    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Binds
    abstract Interceptor bindInterceptor(RequestInterceptor interceptor);

    /**
     * 提供 {@link OkHttpClient}
     *
     * @param configuration
     * @param builder
     * @param intercept
     * @param interceptors
     * @param handler
     * @return {@link OkHttpClient}
     */
    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(@Nullable OkhttpConfiguration configuration, OkHttpClient.Builder builder, Interceptor intercept
            , @Nullable List<Interceptor> interceptors, @Nullable GlobalHttpHandler handler) {
        builder
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(intercept);

        if (handler != null)
            builder.addInterceptor(
                    (chain) -> chain.proceed(handler.onHttpRequestBefore(chain, chain.request()))
            );

        if (interceptors != null) {//如果外部提供了interceptor的集合则遍历添加
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (configuration != null)
            configuration.configOkhttp(builder);
        return builder.build();
    }

    public interface RetrofitConfiguration {
        void configRetrofit(Retrofit.Builder builder);
    }

    public interface OkhttpConfiguration {
        void configOkhttp(OkHttpClient.Builder builder);
    }

    public interface RxCacheConfiguration {
        /**
         * 若想自定义 RxCache 的缓存文件夹或者解析方式, 如改成 fastjson
         * 请 {@code return rxCacheBuilder.persistence(cacheDirectory, new FastJsonSpeaker());}, 否则请 {@code return null;}
         *
         * @param context
         * @param builder
         * @return {@link RxCache}
         */
        RxCache configRxCache(Context context, RxCache.Builder builder);
    }
}
