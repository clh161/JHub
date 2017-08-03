package com.jacob.jhub.interactor.impl;

import com.jacob.jhub.api.GitHubService;
import com.jacob.jhub.api.HttpResponse;
import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MainInteractorImpl implements MainInteractor {
    private GitHubService mGitHubService;

    @Inject
    public MainInteractorImpl(GitHubService gitHubService) {
        mGitHubService = gitHubService;
    }

    @Override
    public void getProfile(String orgName, HttpResponse<Profile> response) {
        mGitHubService.getProfile(orgName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response::onSuccess, e -> response.onFailture((Exception) e));
    }

    @Override
    public void getRepositories(String orgName, int page, HttpResponse<List<Repository>> response) {
        mGitHubService.getRepositories(orgName, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response::onSuccess, e -> response.onFailture((Exception) e));
    }
}