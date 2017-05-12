package com.zlthnrtm.l_loaders.network;

import android.support.annotation.NonNull;


import com.zlthnrtm.l_loaders.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Rishad Mustafaev
 */
public interface WeatherService {

    @GET("data/2.5/weather?units=metric&appid=bc0ffae33833bd4d0214451ff2c0d4be")
    Call<City> getWeather(@NonNull @Query("q") String query);

}
