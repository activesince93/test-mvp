package com.test.aassanjobs.presenter;

import android.content.Context;

import com.test.aassanjobs.database.DatabaseHandler;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.utils.CommonUtils;

import java.util.List;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

class DBInteractor {

    private Context context;
    private DatabaseHandler databaseHandler;

    DBInteractor(Context context) {
        this.context = context;
        this.databaseHandler = DatabaseHandler.getInstance(context);
    }

    void getCityList(IDataInteractor iDataInteractor) {
        CommonUtils.showLog(getClass().getSimpleName(), "getCityList()");
        List<City> cityList = databaseHandler.getCityList();
        if(cityList.size() > 0) {
            iDataInteractor.onDataResponse(cityList);
        } else {
            ServerInteractor serverInteractor = new ServerInteractor(context);
            serverInteractor.getCitiesFromServer(iDataInteractor);
        }
    }

    void addCities(List<City> cityList) {
        CommonUtils.showLog(getClass().getSimpleName(), "addCities()");
        databaseHandler.addCities(cityList);
    }
}
