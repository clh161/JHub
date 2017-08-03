package com.jacob.jhub.presenter.impl;

import android.support.annotation.NonNull;

import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.view.MainView;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    @NonNull
    private final MainInteractor mInteractor;

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

    }
}