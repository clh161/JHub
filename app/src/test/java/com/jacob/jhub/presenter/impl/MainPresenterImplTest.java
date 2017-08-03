package com.jacob.jhub.presenter.impl;

import com.jacob.jhub.api.HttpResponse;
import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;
import com.jacob.jhub.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Jacob on 4/8/2017.
 */
public class MainPresenterImplTest {
    @Mock
    MainInteractor mInteractor;
    @Mock
    MainView mView;
    private MainPresenterImpl mPresenter;
    Profile mProfile = new Profile();
    List<Repository> mRepositories = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doAnswer(invocation -> {
            HttpResponse<Profile> response = invocation.getArgument(1);
            response.onSuccess(mProfile);
            return null;
        }).when(mInteractor).getProfile(eq("facebook"), any(HttpResponse.class));
        doAnswer(invocation -> {
            HttpResponse<List<Repository>> response = invocation.getArgument(2);
            response.onSuccess(mRepositories);
            return null;
        }).when(mInteractor).getRepositories(eq("facebook"), any(Integer.class), any(HttpResponse.class));
        mPresenter = new MainPresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
        mPresenter.onStart(true);

    }

    @Test
    public void fetchProfileOnInit() throws Exception {
        verify(mInteractor).getProfile(eq("facebook"), any(HttpResponse.class));
        verify(mView).updateProfile(mProfile);
    }

    @Test
    public void fetchRepositoryOnInit() throws Exception {
        verify(mInteractor).getRepositories(eq("facebook"), eq(1), any(HttpResponse.class));
        verify(mView).setRepositories(any());
    }

    @Test
    public void loadingTest() throws Exception {
        verify(mView).setListLoading(eq(true));
        verify(mView, times(2)).setListLoading(eq(false));
        mPresenter.onListRequestRefresh();
        verify(mView, times(2)).setListLoading(eq(true));
        verify(mView, times(3)).setListLoading(eq(false));
    }

}