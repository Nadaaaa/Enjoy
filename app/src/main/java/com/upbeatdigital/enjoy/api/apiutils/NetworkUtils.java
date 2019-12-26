package com.upbeatdigital.enjoy.api.apiutils;

import com.upbeatdigital.enjoy.api.responsemodels.ResponseGetHome;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NetworkUtils {

    @GET("home")
    Observable<ResponseGetHome> getHome();
}
