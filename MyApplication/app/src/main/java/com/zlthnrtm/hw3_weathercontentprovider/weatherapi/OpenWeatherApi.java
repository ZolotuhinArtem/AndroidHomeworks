package com.zlthnrtm.hw3_weathercontentprovider.weatherapi;


import com.zlthnrtm.hw3_weathercontentprovider.weatherapi.pojo.WeatherPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public interface OpenWeatherApi {

    //"http://api.openweathermap.org/data/2.5/weather?q=kazan,ru&APPID=b9c967d7d9a3857960e1bcb6fbdb75ca"
    String BASE_URL = "http://api.openweathermap.org";

    @GET("data/2.5/weather?appid=b9c967d7d9a3857960e1bcb6fbdb75ca")
    Call<WeatherPojo> getWeather(@Query("q") String cityName);

}
