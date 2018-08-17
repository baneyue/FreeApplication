package com.base.demo;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.BaseApplication;
import com.base.demo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Application应用基类。
 */
public class FreeApplication extends BaseApplication {
    private FreeApplication app;

    @Override
    protected void init() {
        ARouter.init(this);
        app = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
