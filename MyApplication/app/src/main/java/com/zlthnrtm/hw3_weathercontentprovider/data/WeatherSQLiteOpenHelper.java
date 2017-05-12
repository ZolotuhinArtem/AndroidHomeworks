package com.zlthnrtm.hw3_weathercontentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zlthnrtm.hw3_weathercontentprovider.table.CityContract;
import com.zlthnrtm.hw3_weathercontentprovider.table.WeatherContract;

/**
 * Created by arch on 4/8/17.
 */

public class WeatherSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATA_BASE_NAME = "weather.db";

    public static final int CURRENT_VERSION = 1;

    public WeatherSQLiteOpenHelper(Context context) {
        super(context, DATA_BASE_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CityContract.createTable(db);
        WeatherContract.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
