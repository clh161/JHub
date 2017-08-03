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
    private boolean mIsListLoading = false;


    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        //In case a async http call finishes while the app is on background and unable to update the view, this setListLoading may update the view once it comes back to foreground
        setListLoading(mIsListLoading);
        if (viewCreated) {
            mCurrentPage = 1;
            updateProfile();
            updateRepositories(mCurrentPage);
        }
    }

    private void updateRepositories(int page) {
        setListLoading(true);
        mInteractor.getRepositories(mOrgName, page, new HttpResponse<List<Repository>>() {
            @Override
            public void onSuccess(List<Repository> repositories) {
                setListLoading(false);
                if (mView != null)
                    mView.setRepositories(repositories);
            }

            @Override
            public void onFailture(Exception e) {
                setListLoading(mIsListLoading);
                onHttpError(e);
            }
        });
    }

    private void setListLoading(boolean loading) {
        mIsListLoading = loading;
        if (mView != null)
            mView.setListLoading(loading);
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
                onHttpError(e);
            }
        });
    }

    private void onHttpError(Exception e) {
        if (e.getMessage() != null) {
            Log.e(getClass().getSimpleName(), e.getMessage());
            if (mView != null)
                mView.showToast(e.getMessage());
        }
    }

    @Override
    public void onListRequestRefresh() {
        mCurrentPage = 1;
        updateRepositories(mCurrentPage);
    }
}