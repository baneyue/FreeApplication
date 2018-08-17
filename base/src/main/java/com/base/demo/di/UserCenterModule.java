package com.base.demo.di;

import com.base.demo.MyActivity;
import com.base.demo.mvp.model.UserCenterModel;
import com.base.demo.mvp.presenter.UserCenterMvp;

import dagger.Module;
import dagger.Provides;

@Module
public class UserCenterModule {
    @Provides
    static UserCenterMvp.View provideView(MyActivity myActivity) {
        return myActivity;
    }

    @Provides
    static UserCenterMvp.Model provideModel(UserCenterModel model) {
        return model;
    }
}
