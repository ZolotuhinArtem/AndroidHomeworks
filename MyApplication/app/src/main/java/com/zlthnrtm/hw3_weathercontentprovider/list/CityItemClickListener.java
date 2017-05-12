package com.zlthnrtm.hw3_weathercontentprovider.list;

import com.zlthnrtm.hw3_weathercontentprovider.model.City;

/**
 * Created by arch on 4/13/17.
 */

public interface CityItemClickListener {

    void onDeleteCityItemClick(City city);

    void onItemClickListener(City city);
}
