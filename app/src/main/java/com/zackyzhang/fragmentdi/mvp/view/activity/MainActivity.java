package com.zackyzhang.fragmentdi.mvp.view.activity;

import android.os.Bundle;

import com.zackyzhang.fragmentdi.mvp.view.HasComponent;
import com.zackyzhang.fragmentdi.R;
import com.zackyzhang.fragmentdi.mvp.view.fragment.SampleFragment;
import com.zackyzhang.fragmentdi.di.component.DaggerReposComponent;
import com.zackyzhang.fragmentdi.di.component.ReposComponent;

public class MainActivity extends BaseActivity implements HasComponent<ReposComponent> {

    private static final String TAG = "MainActivity";

    private ReposComponent mReposComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.resolveDaggerDependency();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new SampleFragment());
        }
    }

    private void resolveDaggerDependency() {
        this.mReposComponent = DaggerReposComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActicityModule())
                .build();
    }

    @Override
    public ReposComponent getComponent() {
        return mReposComponent;
    }
}
