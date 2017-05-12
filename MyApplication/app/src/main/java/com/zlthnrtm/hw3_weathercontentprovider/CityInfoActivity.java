package com.zlthnrtm.hw3_weathercontentprovider;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zlthnrtm.hw3_weathercontentprovider.data.ContentProviderWeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.WeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.model.Weather;
import com.zlthnrtm.hw3_weathercontentprovider.service.WeatherReceiver;
import com.zlthnrtm.hw3_weathercontentprovider.service.WeatherService;
import com.zlthnrtm.hw3_weathercontentprovider.table.CityContract;

import java.text.DateFormat;
import java.util.List;

public class CityInfoActivity extends AppCompatActivity implements View.OnClickListener, WeatherReceiver.WeatherReceiverListener {

    public static final String IS_LOADING = "is_loading";

    // VIEWS
    private TextView tvWeather;

    private Button btnUpdate;

    private ProgressBar progerssBar;
    // /VIEWS

    // REPOSITORIES
    private WeatherRepository weatherRepository;
    // /REPOSITORIES

    private WeatherReceiver weatherReceiver;

    private City city;

    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        initViews();
        initData(savedInstanceState);
        initRepositories();
        initRecivers();
        updateWeather();
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            loadState(savedInstanceState);
        } else {
            firstRun();
        }
    }

    private void firstRun() {
        city = CityContract.fromIntent(getIntent());
    }

    private void loadState(Bundle savedInstanceState) {
        city = CityContract.fromBundle(savedInstanceState);
        setLoading(savedInstanceState.getBoolean(IS_LOADING));
    }

    private void saveState(Bundle outState) {
        CityContract.insertToBundle(outState, city);
        outState.putBoolean(IS_LOADING, isLoading);
    }


    private void initRecivers() {
        weatherReceiver = new WeatherReceiver(this);
        IntentFilter intentFilter = new IntentFilter(WeatherService.ACTION_UPDATED);
        registerReceiver(weatherReceiver, intentFilter);
    }

    private void initRepositories() {
        weatherRepository = new ContentProviderWeatherRepository(this);
    }

    private void initViews() {
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        progerssBar = (ProgressBar) findViewById(R.id.pb);
    }

    private void updateWeather() {
        Weather weather = weatherRepository.get(city);
        if (weather != null) {
            tvWeather.setText(formatWeather(weather));
        } else {
            doRequest();
        }
    }

    private String formatWeather(Weather weather) {
        String result = "City: " + weather.getCityName() + "\nTemperature: ";
        if (weather.getTemp() > 0) {
            result += "+";
        } else {
            if (weather.getTemp() < 0) {
                result += "-";
            }
        }
        result += weather.getTemp() + "\nCountryCode: " + weather.getCountryCode() +
                "\nLast update: " + DateFormat.getInstance().format(weather.getDate());

        return result;

    }


    private void toast(String string, int length) {
        Toast.makeText(this, string, length).show();
    }

    private void toast(int resId, int length) {
        Toast.makeText(this, resId, length).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveState(outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(weatherReceiver);
        super.onDestroy();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                doRequest();
                break;
        }

    }

    private void doRequest() {
        WeatherService.start(this, city);
        setLoading(true);
    }

    @Override
    public void onWeatherReceive(String status, String message) {
        switch (status) {
            case WeatherReceiver.STATUS_OK:
                updateWeather();
                break;
            case WeatherReceiver.STATUS_FAIL:
                toast(message, Toast.LENGTH_LONG);
                break;
            default:
                toast("What the hell?", Toast.LENGTH_LONG);
        }
        setLoading(false);
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        int v = View.VISIBLE;
        int g = View.GONE;
        if (loading) {
            tvWeather.setVisibility(g);
            btnUpdate.setVisibility(g);
            progerssBar.setVisibility(v);
        } else {
            tvWeather.setVisibility(v);
            btnUpdate.setVisibility(v);
            progerssBar.setVisibility(g);
        }

    }
}
