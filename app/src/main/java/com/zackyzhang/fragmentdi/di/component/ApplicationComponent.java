package com.zackyzhang.fragmentdi.di.component;

import android.content.Context;

import com.zackyzhang.fragmentdi.BaseActivity;
import com.zackyzhang.fragmentdi.data.GithubService;
import com.zackyzhang.fragmentdi.di.module.ApplicationModule;
import com.zackyzhang.fragmentdi.di.module.GithubServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lei on 3/13/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, GithubServiceModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context getApplication();

    GithubService getGithubService();
}
