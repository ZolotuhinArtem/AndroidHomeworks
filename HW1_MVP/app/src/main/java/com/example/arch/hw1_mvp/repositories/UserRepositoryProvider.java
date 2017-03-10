package com.example.arch.hw1_mvp.repositories;

import com.example.arch.hw1_mvp.model.User;

/**
 * Created by arch on 3/8/17.
 */

public class UserRepositoryProvider {

    private static UserRepository userRepository = null;

    public static UserRepository getUserRepository(){
        return userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        UserRepositoryProvider.userRepository = userRepository;
    }


}
