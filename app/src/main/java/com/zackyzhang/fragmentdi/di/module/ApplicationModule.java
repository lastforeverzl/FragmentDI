package com.zackyzhang.fragmentdi.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lei on 3/13/17.
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context getContext() {
        return this.mContext;
    }

}
