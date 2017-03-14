package com.zackyzhang.fragmentdi.mvp.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.zackyzhang.fragmentdi.MyApplication;
import com.zackyzhang.fragmentdi.data.GithubService;
import com.zackyzhang.fragmentdi.di.component.ApplicationComponent;
import com.zackyzhang.fragmentdi.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by lei on 3/13/17.
 */

public class BaseActivity extends Activity {

    @Inject
    GithubService mGithubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);

    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActicityModule() {
        return new ActivityModule(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
}
