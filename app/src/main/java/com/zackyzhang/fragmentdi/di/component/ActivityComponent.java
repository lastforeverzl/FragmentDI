package com.zackyzhang.fragmentdi.di.component;

import android.app.Activity;

import com.zackyzhang.fragmentdi.di.module.ActivityModule;
import com.zackyzhang.fragmentdi.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by lei on 3/13/17.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    Activity getActivity();
}
