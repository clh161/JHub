package com.jacob.jhub.model;

import java.util.List;

/**
 * Created by Jacob on 3/8/2017.
 */

public class ProfileAndRepositories {
    private final Profile mProfile;
    private final List<Repository> mRepositories;

    public ProfileAndRepositories(Profile profile, List<Repository> repositories) {
        mProfile = profile;
        mRepositories = repositories;
    }

    public Profile getProfile() {
        return mProfile;
    }

    public List<Repository> getRepositories() {
        return mRepositories;
    }
}
