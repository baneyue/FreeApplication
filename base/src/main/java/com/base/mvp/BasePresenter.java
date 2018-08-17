package com.base.mvp;

import com.base.utils.TagUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Presenter基类，用来封装Model,View的依赖。
 * 此外也提供视图生命周期自动管理。
 *
 * @param <V>
 * @param <M>
 */
public abstract class BasePresenter<V extends BaseMvp.IView, M extends BaseMvp.IModel> implements BaseMvp.IPresenter {
    protected static String TAG = TagUtils.makeLogTag(BasePresenter.class);

    @Inject
    protected M model;

    @Inject
    protected V view;

    public BasePresenter(V iView) {
        this.view = iView;
    }

    @Override
    public void subscribe(Disposable disposable) {

    }

    @Override
    public void unSubscribe(Disposable disposable) {

    }
}
