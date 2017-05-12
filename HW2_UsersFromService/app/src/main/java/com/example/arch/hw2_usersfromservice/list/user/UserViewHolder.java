package com.example.arch.hw2_usersfromservice.list.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arch.hw2_usersfromservice.R;
import com.example.arch.hw2_usersfromservice.model.User;

/**
 * Created by arch on 3/14/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView tvUserShort;

    public UserViewHolder(View itemView) {
        super(itemView);
        tvUserShort = (TextView) itemView.findViewById(R.id.tv_usr_short);
    }

    public void bind(@NonNull final User user, final UserListItemClickListener listener) {
        tvUserShort.setText(user.getFirstName() + " " + user.getLastName());

        if (listener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserItemClick(user);
                }
            });
        }
    }
}
