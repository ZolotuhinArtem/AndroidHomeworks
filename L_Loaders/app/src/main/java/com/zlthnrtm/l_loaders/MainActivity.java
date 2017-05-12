package com.zlthnrtm.l_loaders;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zlthnrtm.l_loaders.model.City;
import com.zlthnrtm.l_loaders.model.Weather;
import com.zlthnrtm.l_loaders.recyclerview.CityAdapter;
import com.zlthnrtm.l_loaders.repository.StubWeatherRepository;
import com.zlthnrtm.l_loaders.repository.WeatherRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<City>> {

    public static final int MY_LOADER_ID = 1;

    private RecyclerView cityRecyclerView;

    private SwipeRefreshLayout srl;

    private WeatherRepository weatherRepository;

    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        weatherRepository = new StubWeatherRepository();
        cityAdapter.setList(weatherRepository.getCity());

        srl.setRefreshing(true);
        getLoaderManager().initLoader(MY_LOADER_ID, new Bundle(), this);
    }

    private void initViews() {
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                getLoaderManager().restartLoader(MY_LOADER_ID, new Bundle(), MainActivity.this);
            }
        });
        cityRecyclerView = (RecyclerView) findViewById(R.id.rv_city);
        cityAdapter = new CityAdapter();
        cityRecyclerView.setAdapter(cityAdapter);
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public Loader<List<City>> onCreateLoader(int id, Bundle args) {
        srl.setRefreshing(false);
        return (id == MY_LOADER_ID) ? new WeatherLoader(this, weatherRepository.getCity()) : null;
    }

    @Override
    public void onLoadFinished(Loader<List<City>> loader, List<City> data) {
        srl.setRefreshing(false);
        this.cityAdapter.setList(data);

    }


    @Override
    public void onLoaderReset(Loader<List<City>> loader) {
        srl.setRefreshing(false);
    }
}
