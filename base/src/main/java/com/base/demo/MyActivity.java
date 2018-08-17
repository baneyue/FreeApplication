package com.base.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.BaseActivity;
import com.base.demo.mvp.presenter.UserCenterMvp;
import com.base.demo.mvp.presenter.UserCenterPresenter;
import com.freeman.R;
import com.freeman.R2;
import com.jakewharton.rxbinding2.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

@Route(path = "/user/login")
public class MyActivity extends BaseActivity<UserCenterPresenter> implements UserCenterMvp.View {

    @BindView(R2.id.login)
    Button login;

    @BindView(R2.id.username)
    EditText username;

    @BindView(R2.id.password)
    EditText password;

    @Override
    protected int initLayout() {
        return R.layout.layout_demo;
    }

    @Override
    protected void initViewData(@Nullable Bundle savedInstanceState, @NotNull Intent data) {
        presenter.login("aaa", "bbb");

        RxView.clicks(login)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(v -> presenter.login(username.getText().toString().trim(), password.getText().toString().trim()));
    }

    @Override
    public void loginSucc() {
        Toast.makeText(this, "你成功了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onToast(int serviceType, String msg) {

    }

    @Override
    public void onHideLoading() {

    }
}
