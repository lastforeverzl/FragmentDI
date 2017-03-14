package com.zackyzhang.fragmentdi.mvp.presenter;

import android.widget.Toast;

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
import timber.log.Timber;

/**
 * Created by lei on 3/13/17.
 */
@PerActivity
public class SamplePresenter implements ReposContract.presenter{

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
                .subscribe(repos -> this.showMovieListInView(repos),
                        error -> Timber.tag(TAG).d(error.getMessage()),
                        () -> this.mReposView.showMessage("Loaded!"),
                        subscription -> subscription.request(1)
                );
    }
}
