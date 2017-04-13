package com.zlthnrtm.hw3_weathercontentprovider.data;

import com.zlthnrtm.hw3_weathercontentprovider.model.City;

import java.util.List;

/**
 * Created by arch on 4/12/17.
 */

public interface CityRepository {

    List<City> getAll();

    void add(City city);

    void remove(City city);

}
