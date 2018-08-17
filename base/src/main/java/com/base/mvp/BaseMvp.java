package com.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface BaseMvp {

    /**
     * 操作数据层。
     * 可以操作网络、缓存、磁盘。
     * 这里在项目里面可以作为经典的Retrofit Service层
     */
    public interface IModel {
        Observable subscribe(Observable observable);
        public void onDestory();
    }


    public interface IPresenter extends LifecycleObserver {
        public void subscribe(Disposable disposable);

        public void unSubscribe(Disposable disposable);

        void setLifecycleOwner(LifecycleOwner lifecycleOwner);

//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    void onCreate(@NotNull LifecycleOwner owner);
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    void onStart(@NotNull LifecycleOwner owner);
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    void onResume(@NotNull LifecycleOwner owner);
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    void onPause(@NotNull LifecycleOwner owner);
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    void onStop(@NotNull LifecycleOwner owner);
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    void onDestroy(@NotNull LifecycleOwner owner);

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                @NotNull Lifecycle.Event event);
    }

    /**
     * 操作视图的几个基础接口。
     */
    public interface IView {
        public void onToast(int serviceType, String msg);
        public void onHideLoading();
    }
}
