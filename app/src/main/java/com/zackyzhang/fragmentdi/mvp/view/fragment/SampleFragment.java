package com.zackyzhang.fragmentdi.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zackyzhang.fragmentdi.R;
import com.zackyzhang.fragmentdi.mvp.presenter.SamplePresenter;
import com.zackyzhang.fragmentdi.data.GithubRepo;
import com.zackyzhang.fragmentdi.di.component.ReposComponent;
import com.zackyzhang.fragmentdi.mvp.view.ReposContract;
import com.zackyzhang.fragmentdi.mvp.view.adapter.ReposAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by lei on 3/13/17.
 */

public class SampleFragment extends BaseFragment implements ReposContract.View{

    private static final String TAG = "SampleFragment";
    private Unbinder unbinder;
    private List<GithubRepo> mRepos = new ArrayList<>();

    @Inject
    ReposAdapter mReposAdapter;
    @Inject
    SamplePresenter mSamplePresenter;

    @BindView(R.id.rv_repos)
    RecyclerView mRecyclerView;

    public SampleFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_repo_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void renderRepoList(List<GithubRepo> repos) {
        if (repos != null) {
            this.mRepos = repos;
            mReposAdapter.loadRepos(repos);
        }
    }

    @Override
    protected boolean onInjectView() throws IllegalStateException {
        getComponent(ReposComponent.class).inject(this);
        return true;
    }

    @Override
    protected void onViewInjected(Bundle savedInstanceState) {
        super.onViewInjected(savedInstanceState);
        setupRecyclerView();
        this.mSamplePresenter.setView(this);
        if (savedInstanceState == null) {
            loadRepos();
        } else {
            this.mRepos = savedInstanceState.getParcelableArrayList("RepoList");
            mReposAdapter.loadRepos(this.mRepos);
        }
    }

    private void setupRecyclerView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context()));
        this.mRecyclerView.setAdapter(mReposAdapter);
    }

    private void loadRepos() {
        Timber.tag(TAG).d("load repos!");
        mSamplePresenter.initialize();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("RepoList", (ArrayList<GithubRepo>) mRepos);
    }

}
