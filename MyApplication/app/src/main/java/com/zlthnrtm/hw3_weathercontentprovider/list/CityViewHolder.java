package com.zlthnrtm.hw3_weathercontentprovider.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zlthnrtm.hw3_weathercontentprovider.R;
import com.zlthnrtm.hw3_weathercontentprovider.model.City;

/**
 * Created by arch on 4/13/17.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCityName;

    private Button btnDelete;

    public CityViewHolder(View itemView) {
        super(itemView);
        tvCityName = (TextView) itemView.findViewById(R.id.tv_city);
        btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
    }

    public void bind(@NonNull final City city, @Nullable final CityItemClickListener listener) {
        tvCityName.setText(city.getName());

        if (listener != null) {
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteCityItemClick(city);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(city);
                }
            });
        }
    }
}
