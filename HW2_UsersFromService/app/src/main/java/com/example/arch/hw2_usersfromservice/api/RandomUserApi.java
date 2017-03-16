package com.example.arch.hw2_usersfromservice.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arch on 3/12/17.
 */

public class RandomUserApi {

    public static final String BASE_URL = "https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb/";


    private static RandomUserApiCaller randomUserApiCaller;

    public static RandomUserApiCaller getCaller(){

        if (randomUserApiCaller == null) {
            randomUserApiCaller = (new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()).create(RandomUserApiCaller.class);
        }

        return randomUserApiCaller;
    }
}
