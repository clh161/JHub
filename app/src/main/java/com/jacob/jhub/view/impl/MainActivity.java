package com.jacob.jhub.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jacob.jhub.R;
import com.jacob.jhub.adapter.RepositoryAdapter;
import com.jacob.jhub.injection.AppComponent;
import com.jacob.jhub.injection.DaggerMainViewComponent;
import com.jacob.jhub.injection.MainViewModule;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;
import com.jacob.jhub.presenter.MainPresenter;
import com.jacob.jhub.presenter.loader.PresenterFactory;
import com.jacob.jhub.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.repoCount)
    TextView mRepoCount;
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RepositoryAdapter mAdapter = new RepositoryAdapter();
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(mLinearLayoutManager);
        mList.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.onListRequestRefresh());
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                mPresenter.onListScroll(totalItemCount, lastVisibleItem);
            }
        });
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
        Glide.with(this).load(profile.getAvatarUrl()).into(mAvatar);
    }

    @Override
    public void setRepositories(List<Repository> repositories) {
        mAdapter.setItems(repositories);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setListLoading(boolean loading) {
        mSwipeRefreshLayout.setRefreshing(loading);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
