package com.test.aassanjobs.data;

import com.test.aassanjobs.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darshan on 13-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class FakeDataProvider {

    private String cityNames[] = {"Vadodara", "Ahmedabad", "Mumbai", "Delhi", "Pune"};
    private String citySlugs[] = {"vadodara", "ahmedabad", "mumbai", "delhi", "pune"};

    public List<City> getCities(int total) {
        List<City> cityList = new ArrayList<>();
        for(int i = 0; i < total; i++) {
            cityList.add(getCity(i));
        }
        return cityList;
    }

    public City getCity(int position) {
        position = getArrayPosition(position);

        City city = new City();
        city.setId(position + 1);
        city.setName(cityNames[position]);
        city.setSlug(citySlugs[position]);
        return city;
    }

    private int getArrayPosition(int position) {
        return position % 5;
    }
}
