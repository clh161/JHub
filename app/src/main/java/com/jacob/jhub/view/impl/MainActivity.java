package com.jacob.jhub.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jacob.jhub.R;
import com.jacob.jhub.injection.AppComponent;
import com.jacob.jhub.injection.DaggerMainViewComponent;
import com.jacob.jhub.injection.MainViewModule;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.presenter.loader.PresenterFactory;
import com.jacob.jhub.view.MainView;

import javax.inject.Inject;


public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
