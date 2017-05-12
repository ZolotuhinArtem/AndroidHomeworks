package com.example.arch.hw1_mvp.views;

import com.example.arch.hw1_mvp.model.User;

/**
 * Created by arch on 06.03.17.
 */

public interface EditView {

    String getFirstName();

    String getSecondName();

    String getAddress();

    User getUser();

    void setUser(User user);

    void showUser(User user);

    void exit();
}
