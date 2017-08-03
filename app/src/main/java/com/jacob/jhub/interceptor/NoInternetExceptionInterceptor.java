package com.jacob.jhub.interceptor;

import android.net.ConnectivityManager;

import com.jacob.jhub.exception.NoInternetException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Jacob on 4/8/2017.
 */

public class NoInternetExceptionInterceptor implements Interceptor {
    private ConnectivityManager mConnectivityManager;

    public NoInternetExceptionInterceptor(ConnectivityManager connectivityManager) {
        mConnectivityManager = connectivityManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (mConnectivityManager.getActiveNetworkInfo() == null || !mConnectivityManager.getActiveNetworkInfo().isConnectedOrConnecting())
            throw new NoInternetException("No Internet");
        return chain.proceed(chain.request());
    }


}
