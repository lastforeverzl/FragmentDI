package com.zackyzhang.fragmentdi.di.module;

import android.app.Activity;

import com.zackyzhang.fragmentdi.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lei on 3/13/17.
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.mActivity;
    }
}
