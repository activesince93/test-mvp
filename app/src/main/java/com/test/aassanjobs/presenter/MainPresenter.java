package com.test.aassanjobs.presenter;

import android.content.Context;

import com.test.aassanjobs.model.City;
import com.test.aassanjobs.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class MainPresenter implements IDataInteractor {

    private List<City> cityList = new ArrayList<>();
    private Context context;
    private ICityPresenter iCityPresenter;

    public MainPresenter(Context context) {
        this.context = context;
    }

    // Get city list
    public void provideCityList(ICityPresenter cityListener) {
        CommonUtils.showLog(getClass().getSimpleName(), "provideCityList()");
        this.iCityPresenter = cityListener;
        DBInteractor dbInteractor = new DBInteractor(context);
        dbInteractor.getCityList(this);
    }

    // Get filtered list
    public void filterCities(String query, ICityFilterPresenter cityFilterListener) {
        CommonUtils.showLog(getClass().getSimpleName(), "filterCities()\nQuery: " + query);
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

    @Override
    public void onDataResponse(List<City> cityList) {
        this.cityList = cityList;
        CommonUtils.showLog(getClass().getSimpleName(), "onDataResponse()\n" + cityList.toString());
        iCityPresenter.onCityResponse(cityList);
    }

    @Override
    public void onDataError(String message) {
        CommonUtils.showLog(getClass().getSimpleName(), "onDataError()\n" + message);
        iCityPresenter.onCityError(message);
    }
}
