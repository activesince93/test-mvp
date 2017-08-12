package com.test.aassanjobs.presenter;

import com.test.aassanjobs.model.City;

import java.util.List;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

interface IDataInteractor {
    void onDataResponse(List<City> cityList);
    void onDataError(String message);
}
