package com.example.arch.hw2_usersfromservice.view;

import com.example.arch.hw2_usersfromservice.model.User;

/**
 * Created by arch on 3/16/17.
 */

public interface UserDetailView {

    User getUser();

    void showUser(User user);
}
