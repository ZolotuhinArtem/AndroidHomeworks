package com.zlthnrtm.l_loaders.repository;

import com.zlthnrtm.l_loaders.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arch on 4/15/17.
 */

public class StubWeatherRepository implements WeatherRepository {

    @Override
    public List<City> getCity() {
        List<City> list = new ArrayList<City>(5);

        list.add(new City("Kazan"));
        list.add(new City("Moscow"));
        list.add(new City("Saratov"));
        list.add(new City("Kirov"));
        list.add(new City("London"));

        return list;
    }
}
