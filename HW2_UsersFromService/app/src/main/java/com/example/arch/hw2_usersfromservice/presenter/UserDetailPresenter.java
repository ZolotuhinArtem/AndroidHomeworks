package com.example.arch.hw2_usersfromservice.presenter;

import com.example.arch.hw2_usersfromservice.view.UserDetailView;

/**
 * Created by arch on 3/16/17.
 */

public class UserDetailPresenter {

    public UserDetailPresenter(UserDetailView view) {
        view.showUser(view.getUser());
    }


}
