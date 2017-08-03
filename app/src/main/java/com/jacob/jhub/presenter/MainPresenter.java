package com.jacob.jhub.presenter;

import com.jacob.jhub.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {

    void onListRequestRefresh();

    void onListScroll(int totalItemCount, int lastVisibleItem);
}