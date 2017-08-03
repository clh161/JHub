package com.jacob.jhub.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jacob.jhub.JHub;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final JHub mApp;

    public AppModule(@NonNull JHub app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public JHub provideApp() {
        return mApp;
    }
}
