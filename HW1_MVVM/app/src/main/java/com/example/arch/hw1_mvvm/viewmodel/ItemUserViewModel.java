package com.example.arch.hw1_mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.utils.UserTools;
import com.example.arch.hw1_mvvm.view.EditActivity;

import java.util.Observable;

/**
 * Created by arch on 3/8/17.
 */

public class ItemUserViewModel extends BaseObservable {


    private User user;
    private Activity activity;

    public ObservableField firstName;
    public ObservableField secondName;
    public ObservableField address;


    public ItemUserViewModel(User user, Activity activity) {
        this.user = user;
        //K O C T bI JI b
        this.activity = activity;

        this.firstName = new ObservableField();
        this.secondName = new ObservableField();
        this.address = new ObservableField();

        this.firstName.set(user.getFirstName());
        this.secondName.set(user.getSecondName());
        this.address.set(user.getAddress());
    }

    public void onItemClick(){
        Intent intent = new Intent(activity, EditActivity.class);
        UserTools.fillIntent(intent, this.getUser());
        activity.startActivityForResult(intent, 0);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }
}
