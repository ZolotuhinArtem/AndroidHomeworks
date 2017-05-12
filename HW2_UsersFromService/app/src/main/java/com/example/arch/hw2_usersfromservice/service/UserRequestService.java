package com.example.arch.hw2_usersfromservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.arch.hw2_usersfromservice.api.RandomUserApi;
import com.example.arch.hw2_usersfromservice.model.User;
import com.example.arch.hw2_usersfromservice.model.Users;
import com.example.arch.hw2_usersfromservice.repository.JsonSharedPreferencesUserRepository;
import com.example.arch.hw2_usersfromservice.repository.UserRepository;
import com.example.arch.hw2_usersfromservice.util.CollectionTools;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by arch on 3/12/17.
 */

public class UserRequestService extends IntentService {


    public static final String USER_RESPONSE_ACTION = "com.example.arch.hw2_userfromservice:user-response-action";
    public static final String ATTR_STATUS = "STATUS";
    public static final String PARAM_STATUS_OK = "STATUS_OK";
    public static final String PARAM_STATUS_ERROR = "STATUS_ERROR";

    public UserRequestService() {
        super("*-<||||>-*");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intentTrash) {
        Intent intent = new Intent(USER_RESPONSE_ACTION);

        SharedPreferences sharedPreferences = getSharedPreferences(
                JsonSharedPreferencesUserRepository.DEFAULT_SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        UserRepository userRepository = new JsonSharedPreferencesUserRepository(
                sharedPreferences,
                JsonSharedPreferencesUserRepository.DEFAULT_KEY);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            Response<Users> usersResponse = RandomUserApi.getCaller().getUsers().execute();
            if (usersResponse.isSuccessful()) {
                List<User> users = usersResponse.body().getUsers();

                userRepository.clear();
                userRepository.add(CollectionTools.changeListToSet(users));

                intent.putExtra(ATTR_STATUS, PARAM_STATUS_OK);
            } else {
                Log.d("user-service", "error");
                intent.putExtra(ATTR_STATUS, PARAM_STATUS_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            intent.putExtra(ATTR_STATUS, PARAM_STATUS_ERROR);

        }

        sendBroadcast(intent);
    }
}
