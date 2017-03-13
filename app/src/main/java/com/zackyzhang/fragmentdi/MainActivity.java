package com.zackyzhang.fragmentdi;

import android.os.Bundle;

import com.zackyzhang.fragmentdi.data.GithubRepo;
import com.zackyzhang.fragmentdi.di.component.DaggerReposComponent;
import com.zackyzhang.fragmentdi.di.component.ReposComponent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements HasComponent<ReposComponent>{

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
