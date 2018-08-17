package com.base.demo.mvp.presenter;

import com.base.mvp.BaseMvp;

import io.reactivex.Observable;

/**
 * 登录MVP接口定义。
 */
public interface UserCenterMvp extends BaseMvp {
    public interface View extends IView {
        public void loginSucc();
    }

    public interface Presenter extends IPresenter {
        public void login(String username, String password);
    }

    public interface Model extends IModel {
        public Observable login(String username, String password);
    }
}
