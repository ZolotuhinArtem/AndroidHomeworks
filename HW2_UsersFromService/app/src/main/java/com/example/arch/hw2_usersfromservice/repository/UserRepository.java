package com.example.arch.hw2_usersfromservice.repository;


import com.example.arch.hw2_usersfromservice.model.User;

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
