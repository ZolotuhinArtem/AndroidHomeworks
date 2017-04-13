package com.zlthnrtm.hw3_weathercontentprovider.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zlthnrtm.hw3_weathercontentprovider.R;
import com.zlthnrtm.hw3_weathercontentprovider.data.CityRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.ContentProviderCityRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.ContentProviderWeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.WeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.model.Weather;
import com.zlthnrtm.hw3_weathercontentprovider.table.CityContract;
import com.zlthnrtm.hw3_weathercontentprovider.weatherapi.OpenWeatherApi;
import com.zlthnrtm.hw3_weathercontentprovider.weatherapi.pojo.WeatherPojo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arch on 4/8/17.
 */

public class WeatherService extends IntentService {

    public static final String ACTION_UPDATED = "com.zlthnrtm.hw3_weathercontentprovider.service.WeatherService";

    public WeatherService() {
        super(WeatherService.class.getName());
    }

    public static void start(@NonNull Context context, @NonNull City city) {

        Intent intent = new Intent(context, WeatherService.class);
        CityContract.insertToIntent(intent, city);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        WeatherRepository weatherRepository = new ContentProviderWeatherRepository(getApplicationContext());
        CityRepository cityRepository = new ContentProviderCityRepository(getApplicationContext());

        String status = WeatherReceiver.STATUS_OK;
        String message = "";
        List<City> cityList = cityRepository.getAll();
        City city = CityContract.fromIntent(intent);
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OpenWeatherApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            OpenWeatherApi weatherApi = retrofit.create(OpenWeatherApi.class);
            Call<WeatherPojo> weatherCall = weatherApi.getWeather(city.getName());
            Response<WeatherPojo> response = weatherCall.execute();
            int code = response.code();
            if (response.isSuccessful() && (code < 400)) {
                WeatherPojo weatherPojo = response.body();
                Weather weather = new Weather(
                        new Date(System.currentTimeMillis()),
                        city.getName(),
                        weatherPojo.getSys().getCountry(),
                        weatherPojo.getMain().getTemp() - 273.15);
                weatherRepository.remove(city.getName());
                weatherRepository.add(weather);
            } else {
                status = WeatherReceiver.STATUS_FAIL;
                message = getApplicationContext().getString(R.string.error_with_code) + ": " + code;
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            status = WeatherReceiver.STATUS_FAIL;
            message = e.getMessage();
        }

        Intent broadcastIntent = new Intent(ACTION_UPDATED);
        broadcastIntent.putExtra(WeatherReceiver.KEY_STATUS, status);
        broadcastIntent.putExtra(WeatherReceiver.KEY_MESSAGE, message);
        sendBroadcast(broadcastIntent);
    }
}
