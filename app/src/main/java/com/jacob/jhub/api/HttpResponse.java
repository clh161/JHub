package com.jacob.jhub.api;

/**
 * Created by Jacob on 3/8/2017.
 */

public interface HttpResponse<T> {
    void onSuccess(T data);

    void onFailture(Exception e);
}
