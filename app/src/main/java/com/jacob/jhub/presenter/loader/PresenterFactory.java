package com.jacob.jhub.presenter.loader;

import android.support.annotation.NonNull;

import com.jacob.jhub.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
