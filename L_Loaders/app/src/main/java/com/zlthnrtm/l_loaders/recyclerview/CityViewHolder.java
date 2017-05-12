package com.zlthnrtm.l_loaders.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zlthnrtm.l_loaders.R;
import com.zlthnrtm.l_loaders.model.City;

/**
 * Created by arch on 4/15/17.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCityName;

    private TextView tvWeather;

    public CityViewHolder(View itemView) {
        super(itemView);

        tvCityName = (TextView) itemView.findViewById(R.id.tv_city_name);
        tvWeather = (TextView) itemView.findViewById(R.id.tv_weather);


    }

    public void bind(City city, CityItemClickListener listener) {

        tvCityName.setText(city.getName());
        if (city.getMain() != null) {

            tvWeather.setText(Integer.toString(city.getMain().getTemp()));
        }

    }
}
