package com.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.DaggerApplication;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Application应用基类。
 */
public abstract class BaseApplication extends DaggerApplication {

    @Inject
    protected ActivityTracker activityTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        activityTracker.register(this);
        handleRxJavaException();
    }

    /**
     * 具体业务初始化
     */
    protected abstract void init();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 处理RxJava2 无法传递的异常。
     * 比如Json解析失败。
     */
    private void handleRxJavaException() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) {
                if (e instanceof UndeliverableException) {
                    e = e.getCause();
                }
                if ((e instanceof IOException)) {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return;
                }
                if (e instanceof InterruptedException) {
                    // fine, some blocking code was interrupted by a dispose call
                    return;
                }
                if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                    // that's likely a bug in the application
                    Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                    return;
                }
                if (e instanceof IllegalStateException) {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                    return;
                }
                Log.e("BaseApplication", "Undeliverable exception", e);
            }
        });
    }
}
