package com.test.aassanjobs.rest;

import com.test.aassanjobs.model.CitiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public interface ApiInterface {
    @GET(".")
    Call<CitiesResponse> getCityList();
}
