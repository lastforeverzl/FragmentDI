package com.zackyzhang.fragmentdi.di.component;

import com.zackyzhang.fragmentdi.mvp.view.fragment.SampleFragment;
import com.zackyzhang.fragmentdi.di.module.ActivityModule;
import com.zackyzhang.fragmentdi.di.module.ReposModule;
import com.zackyzhang.fragmentdi.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by lei on 3/13/17.
 */
@PerActivity
@Component(modules = {ActivityModule.class, ReposModule.class}, dependencies = ApplicationComponent.class)
public interface ReposComponent extends ActivityComponent{

    void inject(SampleFragment sampleFragment);
}
