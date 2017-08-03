package com.jacob.jhub.api;

import com.jacob.jhub.model.Profile;
import com.jacob.jhub.model.ProfileAndRepositories;
import com.jacob.jhub.model.Repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jacob on 3/8/2017.
 */

public interface GitHubService {
    Observable<Profile> getProfile(String orgName);

    Observable<List<Repository>> getRepositories(String orgName, int page);

    Observable<ProfileAndRepositories> getProfileAndRepositories(String orgName, int repoPage);
}
