package com.example.arch.hw2_usersfromservice.api;

import com.example.arch.hw2_usersfromservice.model.Users;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by arch on 3/12/17.
 */

public interface RandomUserApiCaller {

    @GET("?sole")
    Call<Users> getUsers();
}
