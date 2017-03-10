package com.example.arch.hw1_mvvm.view;

import android.app.Activity;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.arch.hw1_mvvm.databinding.ItemUserBinding;
import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.viewmodel.ItemUserViewModel;

/**
 * Created by arch on 3/7/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {


    private ItemUserBinding itemUserBinding;
    private ItemUserViewModel itemUserViewModel;

    public UserViewHolder(ItemUserBinding itemUserBinding) {
        super(itemUserBinding.itemUser);
        this.itemUserBinding = itemUserBinding;
    }

    public void bind(User user) {
        if (this.itemUserBinding.getViewModel() == null) {
                                                                   // K O S T bI JI b
            this.itemUserViewModel = new ItemUserViewModel(user, (Activity) itemView.getContext());
            this.itemUserBinding.setViewModel(this.itemUserViewModel);
        }

        if (this.itemUserViewModel != null) {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemUserViewModel.onItemClick();
                }
            });
        }
    }
}
