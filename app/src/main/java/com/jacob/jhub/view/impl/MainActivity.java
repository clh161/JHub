package com.jacob.jhub.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.jacob.jhub.R;
import com.jacob.jhub.injection.AppComponent;
import com.jacob.jhub.injection.DaggerMainViewComponent;
import com.jacob.jhub.injection.MainViewModule;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.presenter.loader.PresenterFactory;
import com.jacob.jhub.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.repoCount)
    TextView mRepoCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @Override
    public void updateProfile(Profile profile) {
        mName.setText(profile.getName());
        mRepoCount.setText(getResources().getString(R.string.repo_count_prefix) + profile.getPublicRepos());
    }
}
