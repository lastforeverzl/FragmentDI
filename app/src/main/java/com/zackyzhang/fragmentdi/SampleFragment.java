package com.zackyzhang.fragmentdi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zackyzhang.fragmentdi.data.GithubRepo;
import com.zackyzhang.fragmentdi.di.component.ReposComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lei on 3/13/17.
 */

public class SampleFragment extends BaseFragment implements ReposContract.View{

    private Unbinder unbinder;

    @Inject
    ReposAdapter mReposAdapter;
    @Inject
    SamplePresenter mSamplePresenter;

    @BindView(R.id.rv_repos)
    RecyclerView mRecyclerView;

    public SampleFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ReposComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_repo_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSamplePresenter.setView(this);
        if (savedInstanceState == null) {
            loadRepos();
        }
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
            mReposAdapter.loadRepos(repos);
        }
    }

    private void setupRecyclerView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context()));
        this.mRecyclerView.setAdapter(mReposAdapter);
    }

    private void loadRepos() {
        mSamplePresenter.initialize();
    }
}
