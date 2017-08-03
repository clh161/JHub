package com.jacob.jhub.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jacob.jhub.api.HttpResponse;
import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.view.MainView;

import java.util.List;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    @NonNull
    private final MainInteractor mInteractor;
    private final String mOrgName = "facebook";
    private int mCurrentPage = 0;


    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if (viewCreated) {
            mCurrentPage = 1;
            updateProfile();
            updateRepositories(mCurrentPage);
        }
    }

    private void updateRepositories(int page) {
        mInteractor.getRepositories(mOrgName, page, new HttpResponse<List<Repository>>() {
            @Override
            public void onSuccess(List<Repository> repositories) {
            }

            @Override
            public void onFailture(Exception e) {
                if (e.getMessage() != null)
                    Log.e(getClass().getSimpleName(), e.getMessage());
            }
        });
    }

    private void updateProfile() {
        mInteractor.getProfile(mOrgName, new HttpResponse<Profile>() {
            @Override
            public void onSuccess(Profile profile) {
                if (mView != null)
                    mView.updateProfile(profile);
            }

            @Override
            public void onFailture(Exception e) {
                Log.e(getClass().getSimpleName(), e.getMessage());
            }
        });
    }
}