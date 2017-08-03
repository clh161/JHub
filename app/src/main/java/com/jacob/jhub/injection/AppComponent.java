package com.jacob.jhub.injection;

import android.content.Context;

import com.jacob.jhub.JHub;
import com.jacob.jhub.api.GitHubService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    JHub getApp();

    GitHubService provideGitHubService();
}