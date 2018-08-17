package com.base.demo.di;

import com.base.demo.MyActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    /*这里集中配置依赖关联，不需要编写每个Component了*/
    @ContributesAndroidInjector(modules = UserCenterModule.class)
    abstract MyActivity myActivityInjector();
}
