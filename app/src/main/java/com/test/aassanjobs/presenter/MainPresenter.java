package com.test.aassanjobs.presenter;

import android.content.Context;

import com.test.aassanjobs.database.DatabaseHandler;
import com.test.aassanjobs.R;
import com.test.aassanjobs.model.CitiesResponse;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.rest.ApiClient;
import com.test.aassanjobs.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class MainPresenter {

    private Context context;
    private DatabaseHandler databaseHandler;
    private List<City> cityList = new ArrayList<>();
    private ApiInterface apiInterface;

    public MainPresenter(Context context) {
        this.context = context;
        this.databaseHandler = DatabaseHandler.getInstance(context);
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    // Get city list
    public void provideCityList(CityListener cityListener) {
        cityList = databaseHandler.getCityList();
        if(cityList.size() > 0) {
            cityListener.onCityResponse(cityList);
        } else {
            getCitiesFromServer(cityListener);
        }
    }

    private void getCitiesFromServer(final CityListener cityListener) {
        Call<CitiesResponse> citiesResponseCall = apiInterface.getCityList();
        citiesResponseCall.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                cityList = response.body().getCityList();
                cityListener.onCityResponse(cityList);

                // Add cities in database
                databaseHandler.addCities(cityList);
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {
                cityListener.onCityError(context.getString(R.string.some_error_occurred));
            }
        });
    }

    // Get filtered list
    public void filterCities(String query, CityFilterListener cityFilterListener) {
        if(query == null || query.equals("")) {
            cityFilterListener.onCityFiltered(cityList);
            return;
        }

        List<City> filteredCityList = new ArrayList<>();
        for(City city : cityList) {
            if(city.getName().toLowerCase().contains(query.toLowerCase())
                    || city.getSlug().toLowerCase().contains(query.toLowerCase())) {
                filteredCityList.add(city);
            }
        }
        cityFilterListener.onCityFiltered(filteredCityList);
    }

    public interface CityListener {
        void onCityResponse(List<City> cityList);
        void onCityError(String message);
    }

    public interface CityFilterListener {
        void onCityFiltered(List<City> cityList);
    }
}
