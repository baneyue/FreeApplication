package com.base.demo.di;

import com.base.ActivityTracker;

//@Module
public class ApplicationModule {

//    @Provides
    ActivityTracker provideActivityTracker() {
        return new ActivityTracker();
    }
}
