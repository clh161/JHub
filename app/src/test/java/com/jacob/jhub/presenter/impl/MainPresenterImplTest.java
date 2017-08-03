package com.jacob.jhub.presenter.impl;

import com.jacob.jhub.api.HttpResponse;
import com.jacob.jhub.interactor.MainInteractor;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
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
    @Mock
    Profile mProfile;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doAnswer(invocation -> {
            HttpResponse<Profile> response = invocation.getArgument(1);
            response.onSuccess(mProfile);
            return invocation.getArgument(1);
        }).when(mInteractor).getProfile(eq("facebook"), any(HttpResponse.class));
        mPresenter = new MainPresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
        mPresenter.onStart(true);

    }

    @Test
    public void fetchProfileOnInit() throws Exception {
        verify(mInteractor).getProfile(eq("facebook"), any(HttpResponse.class));
        verify(mView).updateProfile(mProfile);
    }

}