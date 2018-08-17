package com.base;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.mvp.BaseMvp;
import com.base.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by QingMei on 2017/8/14.
 */

public abstract class BaseFragment<P extends BaseMvp.IPresenter> extends DaggerFragment implements BaseMvp.IView {

    protected View rootView;

    @Inject
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(initLayout(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLifecycleObserver(getLifecycle());
        initView(view);
        initData(savedInstanceState, getArguments());
    }

    @Override
    public void onDestroy() {
        this.rootView = null;
        super.onDestroy();
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NotNull Lifecycle lifecycle) {
        presenter.setLifecycleOwner(this);
        lifecycle.addObserver(presenter);
    }

    @MainThread
    protected abstract void initView(View view);

    @MainThread
    protected abstract void initData(@Nullable Bundle savedInstanceState, @Nullable Bundle data);

    @MainThread
    protected abstract int initLayout();

}
