package com.zlthnrtm.l_loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.zlthnrtm.l_loaders.model.City;
import com.zlthnrtm.l_loaders.network.ApiFactory;
import com.zlthnrtm.l_loaders.network.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arch on 4/15/17.
 */

public class WeatherLoader extends AsyncTaskLoader<List<City>> {

    private List<City> list;

    public WeatherLoader(Context context, List<City> list) {
        super(context);
        this.list = list;
        forceLoad();
    }



    @Override
    public List<City> loadInBackground() {
        List<City> newList = new ArrayList<>();
        for (City i: list) {
            try {
                newList.add(ApiFactory.getWeatherService().getWeather(i.getName()).execute().body());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newList;
    }
}
