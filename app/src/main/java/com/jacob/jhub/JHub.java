package com.jacob.jhub;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jacob.jhub.injection.AppComponent;
import com.jacob.jhub.injection.AppModule;
import com.jacob.jhub.injection.DaggerAppComponent;

public final class JHub extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}