package com.zlthnrtm.hw3_weathercontentprovider.data;

import android.content.Context;
import android.database.Cursor;

import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.model.Weather;
import com.zlthnrtm.hw3_weathercontentprovider.table.WeatherContract;


/**
 * Created by arch on 4/12/17.
 */

public class ContentProviderWeatherRepository implements WeatherRepository {
    private Context context;

    public ContentProviderWeatherRepository(Context context) {
        this.context = context;
    }

    @Override
    public Weather get(City city) {
        Weather weather = null;
        Cursor cursor = context.getContentResolver().query(WeatherContract.getUri(),
                null,
                WeatherContract.WeatherEntry.COLUMN_CITY_NAME + "=?",
                new String[]{city.getName()}, null);
        while (cursor.moveToNext()) {
            weather = WeatherContract.fromCursor(cursor);
        }
        cursor.close();

        return weather;
    }

    @Override
    public void add(Weather weather) {
        context.getContentResolver().insert(WeatherContract.getUri(),
                WeatherContract.toContentValues(weather));
    }

    @Override
    public void remove(String cityName) {
        context.getContentResolver().delete(WeatherContract.getUri(),
                WeatherContract.WeatherEntry.COLUMN_CITY_NAME + "=?", new String[]{cityName});
    }
}
