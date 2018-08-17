package com.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

//import com.libs.core.common.utils.LogUtils;

import java.util.Stack;

import javax.inject.Inject;

/**
 * Activity状态追踪器
 * 1、解决传统在Activity基类中监听Activity生命周期的弊端
 * 2、此类在Application基类中注册自动监听Activity生命周期（一行代码即可）
 * 3、实时掌握当前应用是否在前台运行
 * 4、该类适用于API4.0++
 *
 * @author zhang.zheng
 * @version 2018-05-08
 */
public class ActivityTracker implements Application.ActivityLifecycleCallbacks {

    private Stack<Activity> mActivityStack = new Stack<>();
    private boolean mIsForeground;
    private int mActiveCount;
    private long timestamp;


    @Inject
    public ActivityTracker() {
    }

    public void register(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }


    /**
     * 应用是否在前台
     */
    public boolean isForeground() {
        return mIsForeground;
    }

    /**
     * 获取activity栈个数
     */
    public int getStackSize() {
        return mActivityStack.size();
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity == null)
            return;
        try {
            mActivityStack.push(activity);
        } catch (Exception e) {
//            LogUtils.e(this, "e:" + e);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (mActiveCount == 0) {
            timestamp = System.currentTimeMillis();
        }
        mActiveCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mIsForeground = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActiveCount--;
        if (mActiveCount == 0) {
            mIsForeground = false;
            timestamp = System.currentTimeMillis() - timestamp;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity == null)
            return;
        try {
            mActivityStack.remove(activity);
        } catch (Exception e) {
//            LogUtils.e(this, "e:" + e);
        }
    }
}
