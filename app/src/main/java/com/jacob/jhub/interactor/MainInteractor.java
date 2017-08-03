package com.jacob.jhub.interactor;

import com.jacob.jhub.api.HttpResponse;
import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.Repository;

import java.util.List;

public interface MainInteractor extends BaseInteractor {

    void getProfile(String orgName, HttpResponse<Profile> response);

    void getRepositories(String orgName, int page, HttpResponse<List<Repository>> response);
}