package com.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.base.mvp.BaseMvp;
import com.base.mvp.BasePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.HasFragmentInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<P extends BasePresenter> extends DaggerAppCompatActivity implements LifecycleObserver, HasFragmentInjector, HasSupportFragmentInjector, BaseMvp.IView {

    protected Unbinder mUnBinder;

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initLifecycleObserver(getLifecycle());
        mUnBinder = ButterKnife.bind(this);
        initViewData(savedInstanceState, getIntent());
    }

    @MainThread
    protected abstract int initLayout();

    @MainThread
    protected abstract void initViewData(@Nullable Bundle savedInstanceState, @NotNull Intent data);

    @MainThread
    protected void initLifecycleObserver(@NotNull Lifecycle lifecycle) {
        presenter.setLifecycleOwner(this);
        lifecycle.addObserver(presenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null)
            mUnBinder.unbind();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            // 点击屏幕空白隐藏软键盘
            View view = getCurrentFocus();
            if (view != null && view.getWindowToken() != null) {
                InputMethodManager manager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
}
