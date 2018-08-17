package com.base.demo.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.base.demo.mvp.model.bean.User;
import com.base.demo.mvp.model.bean.WebGsonResult;
import com.base.mvp.BasePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserCenterPresenter extends BasePresenter<UserCenterMvp.View, UserCenterMvp.Model> implements UserCenterMvp.Presenter {

    @Inject
    public UserCenterPresenter(UserCenterMvp.View view) {
        super(view);
    }

    public void login(String username, String password) {
        model.login(username, password).subscribe(new Observer<WebGsonResult<User>>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(WebGsonResult<User> user) {
                Log.d(TAG, "onNext: " + user);
                view.loginSucc();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage(), e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Over!");
            }
        });
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {

    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
