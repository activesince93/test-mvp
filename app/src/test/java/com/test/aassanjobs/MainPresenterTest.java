package com.test.aassanjobs;

import com.test.aassanjobs.data.FakeDataProvider;
import com.test.aassanjobs.model.City;
import com.test.aassanjobs.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class MainPresenterTest {

    private MainPresenter mainPresenter;
    private FakeDataProvider fakeDataProvider;
    private List<City> cityList;
    private City city;
    private static final int ARRAY_SIZE = 10;

    @Before
     public void setupPresenter() {
        mainPresenter = new MainPresenter();
        fakeDataProvider = new FakeDataProvider();
        cityList = fakeDataProvider.getCities(ARRAY_SIZE);
        city = fakeDataProvider.getCity(0);
    }

    @Test
    public void checkCityFilter() {
        List<City> cityList = mainPresenter.filteredCities("mumbai", this.cityList);
        assertEquals(cityList.size(), 2);
    }

    @Test
    public void checkCityListSize() {
        assertEquals(cityList.size(), ARRAY_SIZE);
    }

    @Test
    public void checkCityName() {
        assertEquals(city.getName(), cityList.get(0).getName());
    }

    @Test
    public void checkCityId() {
        assertEquals(city.getId(), cityList.get(0).getId());
    }

    @Test
    public void checkCitySlug() {
        assertEquals(city.getSlug(), cityList.get(0).getSlug());
    }
}