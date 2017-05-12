package com.zlthnrtm.hw3_weathercontentprovider.data;

import android.content.Context;
import android.database.Cursor;

import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.table.CityContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arch on 4/12/17.
 */

public class ContentProviderCityRepository implements CityRepository {

    private Context context;

    public ContentProviderCityRepository(Context context) {
        this.context = context;
    }

    @Override
    public List<City> getAll() {
        List<City> cityList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CityContract.getUri(), null, null, null, null);
        while (cursor.moveToNext()) {
            cityList.add(CityContract.fromCursor(cursor));
        }
        cursor.close();
        return cityList;
    }

    @Override
    public void add(City city) {
        context.getContentResolver().insert(CityContract.getUri(),
                CityContract.toContentValues(city));
    }

    @Override
    public void remove(City city) {
        context.getContentResolver().delete(CityContract.getUri(),
                CityContract.CityEntry.COLUMN_NAME + "=?",
                new String[]{city.getName()});
    }

}
