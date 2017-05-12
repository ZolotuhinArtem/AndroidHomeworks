package com.example.arch.hw1_mvvm.repositories;


import com.example.arch.hw1_mvvm.model.User;

import java.util.Set;

/**
 * Created by arch on 3/7/17.
 */

public interface UserRepository {

    Set<User> getAll();

    void add(User user);

    void add(Set<User> users);

    void delete(User user);

    void clear();

}
