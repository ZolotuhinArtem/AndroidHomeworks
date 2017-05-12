package com.example.arch.hw1_mvvm.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.arch.hw1_mvvm.R;
import com.example.arch.hw1_mvvm.databinding.ItemUserBinding;
import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.view.UserViewHolder;

import java.util.List;

/**
 * Created by arch on 3/7/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> list;
    private Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding itemUserBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (holder != null) {
            User user = this.list.get(position);
            if (user != null) {
                holder.bind(user);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (this.list != null) {
            return list.size();
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

}
