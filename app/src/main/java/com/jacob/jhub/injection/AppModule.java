package com.jacob.jhub.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.jacob.jhub.JHub;
import com.jacob.jhub.api.GitHubService;
import com.jacob.jhub.api.GitHubServiceImpl;
import com.jacob.jhub.api.GitHubServiceRetrofit;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    /**
     * Make Retrofit service for GitHub
     *
     * @return Guthub service interface
     */
    @Provides
    public GitHubService provideGitHubService() {
        GitHubServiceRetrofit retrofit = getRetrofitService("https://api.github.com/", GitHubServiceRetrofit.class);
        return new GitHubServiceImpl(retrofit);
    }

    /**
     * Generate Retrofit interface for api service. The generic type tClass allow dynamic return of difference Retrofit class
     *
     * @param baseUrl base url of service endpoint
     * @param tClass  Retrofit class
     * @return Retrofit service interface
     */
    private <T> T getRetrofitService(String baseUrl, Class<T> tClass) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build()
                .create(tClass);
    }

}
