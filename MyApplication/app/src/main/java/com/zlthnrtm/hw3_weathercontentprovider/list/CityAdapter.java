package com.zlthnrtm.hw3_weathercontentprovider.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlthnrtm.hw3_weathercontentprovider.R;
import com.zlthnrtm.hw3_weathercontentprovider.model.City;

import java.util.List;

/**
 * Created by arch on 4/13/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> list;

    private CityItemClickListener listener;

    public CityAdapter() {
    }

    public CityAdapter(List<City> list, CityItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        notifyDataSetChanged();
    }


    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = list.get(position);
        if (city != null) {
            holder.bind(city, listener);
        }
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }

    public void add(City city) {
        list.add(city);
        notifyDataSetChanged();
    }

    public void remove(City city) {
        list.remove(city);
        notifyDataSetChanged();
    }

    public void setList(List<City> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setListener(CityItemClickListener listener) {
        this.listener = listener;
    }
}
