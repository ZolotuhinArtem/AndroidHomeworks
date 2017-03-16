package com.example.arch.hw2_usersfromservice.view;

import com.example.arch.hw2_usersfromservice.model.User;

import java.util.List;

/**
 * Created by arch on 3/14/17.
 */

public interface UsersView {

    void showUsers(List<User> users);

    void showError(String s);

    void showMessage(String message);

    void goToUserDetailView(User user);

    void setLoading(boolean isLoading);
}
