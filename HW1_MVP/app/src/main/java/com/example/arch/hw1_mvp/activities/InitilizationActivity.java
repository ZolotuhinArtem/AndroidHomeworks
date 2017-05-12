package com.example.arch.hw1_mvp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arch.hw1_mvp.R;
import com.example.arch.hw1_mvp.repositories.JsonSharedPreferencesUserRepository;
import com.example.arch.hw1_mvp.repositories.UserRepository;
import com.example.arch.hw1_mvp.repositories.UserRepositoryProvider;

public class InitilizationActivity extends AppCompatActivity {


    public static String SH_NAME = "user_data";
    public static String SH_KEY = "user_list";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initilization);

        SharedPreferences sharedPreferences = getSharedPreferences(SH_NAME, MODE_PRIVATE);
        UserRepository userRepository = new JsonSharedPreferencesUserRepository(sharedPreferences, SH_KEY);
        UserRepositoryProvider.setUserRepository(userRepository);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
