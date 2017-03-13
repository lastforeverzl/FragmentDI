package com.zackyzhang.fragmentdi.data;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lei on 3/13/17.
 */

public interface GithubService {

    @GET("users/{username}/repos")
    Flowable<List<GithubRepo>> getReposForUser(@Path("username") String username);

}
