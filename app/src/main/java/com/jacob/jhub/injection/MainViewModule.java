package com.jacob.jhub.injection;

import android.support.annotation.NonNull;

import com.jacob.jhub.api.GitHubService;
import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.interactor.impl.MainInteractorImpl;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.presenter.impl.MainPresenterImpl;
import com.jacob.jhub.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {
    @Provides
    public MainInteractor provideInteractor(GitHubService gitHubService) {
        return new MainInteractorImpl(gitHubService);
    }

    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return () -> new MainPresenterImpl(interactor);
    }
}
