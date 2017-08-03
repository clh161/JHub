package com.jacob.jhub.api;

import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jacob on 3/8/2017.
 */

public interface GitHubServiceRetrofit {
    @GET("users/{org}")
    Observable<Profile> getProfile(@Path("org") String org);

    @GET("orgs/{org}/repos")
    Observable<List<Repository>> getRepositories(@Path("org") String org, @Query("page") int page);

}
