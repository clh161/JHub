package com.jacob.jhub.view;

import android.support.annotation.UiThread;

import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;

import java.util.List;

@UiThread
public interface MainView {

    void updateProfile(Profile profile);

    void setRepositories(List<Repository> repositories);

    void setListLoading(boolean loading);
}