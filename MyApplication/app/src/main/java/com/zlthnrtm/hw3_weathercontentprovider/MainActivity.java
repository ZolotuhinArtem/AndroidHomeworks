package com.zlthnrtm.hw3_weathercontentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zlthnrtm.hw3_weathercontentprovider.data.CityRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.ContentProviderCityRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.ContentProviderWeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.data.WeatherRepository;
import com.zlthnrtm.hw3_weathercontentprovider.list.CityAdapter;
import com.zlthnrtm.hw3_weathercontentprovider.list.CityItemClickListener;
import com.zlthnrtm.hw3_weathercontentprovider.model.City;
import com.zlthnrtm.hw3_weathercontentprovider.table.CityContract;
import com.zlthnrtm.hw3_weathercontentprovider.utils.StringUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CityItemClickListener {

    // VIEWS
    private RecyclerView cityRecyclerView;

    private Button btnAddCity;

    private EditText etCityName;
    // /VIEWS

    // REPOSITORIES
    private CityRepository cityRepository;

    private WeatherRepository weatherRepository;
    // /REPOSITORIES

    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRepositories();
        initRecyclerView();
        updateCities();
    }

    private void initRepositories() {
        cityRepository = new ContentProviderCityRepository(this);
        weatherRepository = new ContentProviderWeatherRepository(this);
    }


    private void initRecyclerView() {
        cityAdapter = new CityAdapter();
        cityAdapter.setListener(this);
        cityRecyclerView.setAdapter(cityAdapter);
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViews() {
        cityRecyclerView = (RecyclerView) findViewById(R.id.rv_city);
        btnAddCity = (Button) findViewById(R.id.btn_add_city);
        btnAddCity.setOnClickListener(this);
        etCityName = (EditText) findViewById(R.id.et_city_name);
    }

    private void updateCities() {
        List<City> cityList = cityRepository.getAll();
        cityAdapter.setList(cityList);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_city:
                btnAddCityClicked(v);
                break;
        }
    }

    private void btnAddCityClicked(View v) {

        String cityName = StringUtils.deleteSpacesLeftAndRight(etCityName.getText().toString());

        if (StringUtils.isEmptyOrSpace(cityName)) {
            toast(R.string.city_name_is_empty, Toast.LENGTH_SHORT);
        } else {
            addCity(cityName);
        }
    }

    private void addCity(String cityName) {
        City city = new City(cityName);
        cityRepository.add(city);
        updateCities();
    }

    private void removeCity(City city) {
        weatherRepository.remove(city.getName());
        cityRepository.remove(city);
        cityAdapter.remove(city);

    }
    private void toast(String string, int length) {
        Toast.makeText(this, string, length).show();
    }

    private void toast(int resId, int length) {
        Toast.makeText(this, resId, length).show();
    }

    @Override
    public void onDeleteCityItemClick(City city) {
        removeCity(city);
    }

    @Override
    public void onItemClickListener(City city) {
        Intent intent = new Intent(this, CityInfoActivity.class);
        CityContract.insertToIntent(intent, city);
        startActivity(intent);
    }
}
