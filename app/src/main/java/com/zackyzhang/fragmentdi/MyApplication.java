package com.zackyzhang.fragmentdi;

import android.app.Application;

import com.zackyzhang.fragmentdi.di.component.ApplicationComponent;
import com.zackyzhang.fragmentdi.di.component.DaggerApplicationComponent;
import com.zackyzhang.fragmentdi.di.module.ApplicationModule;
import com.zackyzhang.fragmentdi.di.module.GithubServiceModule;

import timber.log.Timber;

/**
 * Created by lei on 3/13/17.
 */

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .githubServiceModule(new GithubServiceModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
