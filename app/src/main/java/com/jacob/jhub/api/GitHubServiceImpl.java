package com.jacob.jhub.api;

import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.ProfileAndRepositories;
import com.jacob.jhub.model.Repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jacob on 3/8/2017.
 */

public class GitHubServiceImpl implements GitHubService {
    private GitHubServiceRetrofit mRetrofit;

    public GitHubServiceImpl(GitHubServiceRetrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<Profile> getProfile(String orgName) {
        return mRetrofit.getProfile(orgName);
    }

    @Override
    public Observable<List<Repository>> getRepositories(String orgName, int page) {
        return mRetrofit.getRepositories(orgName, page);
    }

    /**
     * This is an example why using three classes - GitHubService, GitHubServiceImpl and GitHubServiceRetrofit, instead of just using one class GitHubServiceRetrofit.
     * With GitHubService and GitHubServiceImpl, we can apply more functions to a call with RX, like chain calls with .flapMap(), simultaneous calls with .zip()
     */
    @Override
    public Observable<ProfileAndRepositories> getProfileAndRepositories(String orgName, int repoPage) {
        return Observable.zip(getProfile(orgName), getRepositories(orgName, repoPage), ProfileAndRepositories::new);
    }


}
