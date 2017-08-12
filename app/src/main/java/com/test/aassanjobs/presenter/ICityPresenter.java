package com.test.aassanjobs.presenter;

import com.test.aassanjobs.model.City;

import java.util.List;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public interface ICityPresenter {
    void onCityResponse(List<City> cityList);
    void onCityError(String message);
}

