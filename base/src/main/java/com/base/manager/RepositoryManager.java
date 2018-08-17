package com.base.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import retrofit2.Retrofit;

@Singleton
public class RepositoryManager {
    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    Lazy<RxCache> mRxCache;


    private Cache<String,Object> mRetrofitServiceCache;


    private Cache<String,Object> mCacheServiceCache;

    @Inject
    public RepositoryManager(){

    }

    public synchronized <T> T obtainRetrofitService(Class<T> clazz){
        T retrofitService = (T)mRetrofitServiceCache.get(service.getCanonicalName());
        if(retrofitService == null){

        }
    }

}
