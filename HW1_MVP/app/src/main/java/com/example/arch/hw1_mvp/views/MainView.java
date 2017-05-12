package com.example.arch.hw1_mvp.views;

import com.example.arch.hw1_mvp.model.User;

import java.util.List;

/**
 * Created by arch on 06.03.17.
 */

public interface MainView {

    void goToEditActivity(User user);

    void setUserList(List<User> list);

}
