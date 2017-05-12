package com.zlthnrtm.hw3_weathercontentprovider.data;

import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.model.Weather;

import java.util.List;

/**
 * Created by arch on 4/12/17.
 */

public interface WeatherRepository {


    Weather get(City city);

    void add(Weather weather);

    void remove(String cityName);


}
