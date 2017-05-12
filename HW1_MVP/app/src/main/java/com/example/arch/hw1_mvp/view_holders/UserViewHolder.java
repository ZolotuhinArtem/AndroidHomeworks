package com.example.arch.hw1_mvp.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arch.hw1_mvp.R;
import com.example.arch.hw1_mvp.adapters.UserAdapter;
import com.example.arch.hw1_mvp.model.User;

/**
 * Created by arch on 3/7/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {


    private TextView tvFirstName, tvSecondName, tvAddress;

    public UserViewHolder(View itemView) {
        super(itemView);

        this.tvFirstName = (TextView) itemView.findViewById(R.id.tv_fname);
        this.tvSecondName = (TextView) itemView.findViewById(R.id.tv_sname);
        this.tvAddress = (TextView) itemView.findViewById(R.id.tv_address);

    }

    public void bind(final User user, final UserAdapter.UserClickListener listener) {
        if (user != null) {
            this.tvFirstName.setText(user.getFirstName());
            this.tvSecondName.setText(user.getSecondName());
            this.tvAddress.setText(user.getAddress());
        }

        if (listener != null) {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserClick(user);
                }
            });
        }
    }
}
