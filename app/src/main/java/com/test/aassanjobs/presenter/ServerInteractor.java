package com.test.aassanjobs.presenter;

import android.content.Context;

import com.test.aassanjobs.R;
import com.test.aassanjobs.model.CitiesResponse;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.rest.ApiClient;
import com.test.aassanjobs.rest.ApiInterface;
import com.test.aassanjobs.utils.CommonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

class ServerInteractor {

    private Context context;
    private ApiInterface apiInterface;

    ServerInteractor(Context context) {
        this.context = context;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    void getCitiesFromServer(final IDataInteractor iDataInteractor) {
        CommonUtils.showLog(getClass().getSimpleName(), "getCitiesFromServer()");
        Call<CitiesResponse> citiesResponseCall = apiInterface.getCityList();
        citiesResponseCall.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                List<City> cityList = response.body().getCityList();
                iDataInteractor.onDataResponse(cityList);

                // Add cities in database
                DBInteractor dbInteractor = new DBInteractor(context);
                dbInteractor.addCities(cityList);
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {
                iDataInteractor.onDataError(context.getString(R.string.some_error_occurred));
            }
        });
    }
}
