package com.zackyzhang.fragmentdi.mvp.presenter;

import com.zackyzhang.fragmentdi.data.GithubRepo;
import com.zackyzhang.fragmentdi.data.GithubService;
import com.zackyzhang.fragmentdi.di.scope.PerActivity;
import com.zackyzhang.fragmentdi.mvp.view.ReposContract;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 3/13/17.
 */
@PerActivity
public class SamplePresenter implements ReposContract.presenter, Subscriber<List<GithubRepo>>{

    private static final String TAG = "SamplePresenter";

    private ReposContract.View mReposView;
    private GithubService mGithubService;

    @Inject
    public SamplePresenter(GithubService githubService) {
        this.mGithubService = githubService;
    }

    @Override
    public void setView(ReposContract.View view) {
        mReposView = view;
    }

    public void initialize() {
        this.getRepos();
    }

    private void showMovieListInView(List<GithubRepo> repos) {
        mReposView.renderRepoList(repos);
    }

    private void getRepos() {
        mGithubService.getReposForUser("JakeWharton")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(1);
    }

    @Override
    public void onNext(List<GithubRepo> repos) {
        this.showMovieListInView(repos);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
