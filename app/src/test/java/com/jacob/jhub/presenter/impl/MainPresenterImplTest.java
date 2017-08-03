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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jacob on 4/8/2017.
 */
public class MainPresenterImplTest {
    @Mock
    MainInteractor mInteractor;
    @Mock
    MainView mView;
    private MainPresenterImpl mPresenter;
    @Mock
    Profile mProfile;
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

    /**
     * Test if the presenter adding repositories from response
     */
    @Test
    public void pullToRefresh() throws Exception {
        int responseSize = 5;
        List<Repository> repositories1 = new ArrayList<>();
        for (int i = 0; i < responseSize; i++) {
            repositories1.add(new Repository());
        }
        doAnswer(invocation -> {
            HttpResponse<List<Repository>> response = invocation.getArgument(2);
            response.onSuccess(repositories1);
            return null;
        }).when(mInteractor).getRepositories(eq("facebook"), any(Integer.class), any(HttpResponse.class));
        doAnswer(invocation -> {
            List<Repository> response = invocation.getArgument(0);
            assertEquals(responseSize, response.size());
            for (int i = 0; i < responseSize; i++) {
                assertTrue(repositories1.get(i) == response.get(i));
            }
            assertEquals(responseSize, response.size());
            return null;
        }).when(mView).setRepositories(any());
        mPresenter.onListRequestRefresh();
    }

    /**
     * Test if the presenter adding up repository on onListScroll call
     */
    @Test
    public void fetchMore() throws Exception {
        when(mProfile.getPublicRepos()).thenReturn(1000);
        int responseSize1 = 5;
        List<Repository> repositories1 = new ArrayList<>();
        for (int i = 0; i < responseSize1; i++) {
            repositories1.add(new Repository());
        }
        int responseSize2 = 5;
        List<Repository> repositories2 = new ArrayList<>();
        for (int i = 0; i < responseSize2; i++) {
            repositories2.add(new Repository());
        }
        doAnswer(invocation -> {
            HttpResponse<List<Repository>> response = invocation.getArgument(2);
            response.onSuccess(repositories1);
            return null;
        }).when(mInteractor).getRepositories(eq("facebook"), any(Integer.class), any(HttpResponse.class));
        doAnswer(invocation -> {
            List<Repository> response = invocation.getArgument(0);
            for (int i = 0; i < responseSize1; i++) {
                assertTrue(repositories1.get(i) == response.get(i));
            }
            assertEquals(responseSize1, response.size());
            return null;
        }).when(mView).setRepositories(any());
        mPresenter.onListScroll(10, 10);
        //second fetch
        doAnswer(invocation -> {
            HttpResponse<List<Repository>> response = invocation.getArgument(2);
            response.onSuccess(repositories2);
            return null;
        }).when(mInteractor).getRepositories(eq("facebook"), any(Integer.class), any(HttpResponse.class));
        doAnswer(invocation -> {
            List<Repository> response = invocation.getArgument(0);
            assertEquals(responseSize1 + responseSize2, response.size());
            for (int i = 0; i < responseSize1; i++) {
                assertTrue(repositories1.get(i) == response.get(i));
            }
            for (int i = 0; i < responseSize2; i++) {
                assertTrue(repositories2.get(i) == response.get(i + responseSize1));
            }
            return null;
        }).when(mView).setRepositories(any());
        mPresenter.onListScroll(10, 10);
    }
}