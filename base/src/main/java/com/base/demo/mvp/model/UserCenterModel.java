package com.base.demo.mvp.model;

import com.base.demo.mvp.presenter.UserCenterMvp;
import com.base.mvp.BaseModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UserCenterModel extends BaseModel implements UserCenterMvp.Model {

    @Inject
    public UserCenterModel() {
        super();
    }

    @Override
    public Observable login(String username, String password) {
        return subscribe(casService.login(
                username,
                password,
                "crm",
                "1",
                "0",
                "2"));
    }
}
