package com.example.arch.hw2_usersfromservice.list.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arch.hw2_usersfromservice.R;
import com.example.arch.hw2_usersfromservice.model.User;

import java.util.List;

/**
 * Created by arch on 3/14/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> list;

    private UserListItemClickListener listener;

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            User user = this.getList().get(position);
            if (user != null) {
                holder.bind(user, this.getListener());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (this.getList() != null) {
            return this.getList().size();
        } else {
            return 0;
        }
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public UserListItemClickListener getListener() {
        return listener;
    }

    public void setListener(UserListItemClickListener listener) {
        this.listener = listener;
    }
}
