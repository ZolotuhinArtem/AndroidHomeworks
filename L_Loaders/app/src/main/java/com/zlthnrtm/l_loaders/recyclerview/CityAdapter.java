package com.zlthnrtm.l_loaders.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlthnrtm.l_loaders.R;
import com.zlthnrtm.l_loaders.model.City;

import java.util.List;

/**
 * Created by arch on 4/15/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> list;

    private CityItemClickListener listener;

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
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

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public CityItemClickListener getListener() {
        return listener;
    }

    public void setListener(CityItemClickListener listener) {
        this.listener = listener;
    }
}
