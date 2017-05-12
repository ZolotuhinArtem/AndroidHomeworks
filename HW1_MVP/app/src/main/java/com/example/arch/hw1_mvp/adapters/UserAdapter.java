package com.example.arch.hw1_mvp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arch.hw1_mvp.R;
import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.view_holders.UserViewHolder;

import java.util.List;

/**
 * Created by arch on 3/7/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> list;
    private UserClickListener listener;

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (holder != null) {
            User user = this.list.get(position);
            if (user != null) {
                holder.bind(user, this.listener);
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

    public UserClickListener getListener() {
        return listener;
    }

    public void setListener(UserClickListener listener) {
        this.listener = listener;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface UserClickListener {
        void onUserClick(User user);
    }
}
