package com.base.demo.di;

import com.base.demo.FreeApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,// Android（Activity/Fragment/Service/ContentProvider/Broadcast） 几大组件的依赖模块
        AndroidSupportInjectionModule.class,// 为Support Fragment提供额外的依赖模块，
        AppModule.class,//一些公共基础的全局依赖模块
        BuildersModule.class// 依赖模块构建器，代替Component的编写
})
/**
 * 此处继承AndroidInjector,彻底摆脱在Android四大组件中手动进行Component构建。
 * 从dagger2.11开始支持。
 */
public interface AppComponent extends AndroidInjector<FreeApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<FreeApplication> {
    }
}
