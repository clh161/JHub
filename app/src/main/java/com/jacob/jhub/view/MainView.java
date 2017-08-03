package com.jacob.jhub.view;

import android.support.annotation.UiThread;

import com.jacob.jhub.model.Profile;

@UiThread
public interface MainView {

    void updateProfile(Profile profile);
}