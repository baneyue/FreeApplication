package com.base.mvp;

import android.support.annotation.MainThread;

import com.base.demo.mvp.model.service.CASService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 数据层。
 * 访问网络。
 * 访问缓存。
 * 访问磁盘。
 */
public abstract class BaseModel implements BaseMvp.IModel {
    protected static String TAG = "BaseModel";

    @Inject
    protected CASService casService;

    public BaseModel() {
    }

    /**
     * 返回结果在MainThread处理。
     *
     * @param observable
     */
    @MainThread
    public Observable subscribe(Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestory() {

    }

}
