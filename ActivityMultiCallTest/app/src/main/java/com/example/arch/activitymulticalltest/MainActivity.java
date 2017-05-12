package com.example.arch.activitymulticalltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "main-activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, TestActivity.class);

        for(int i = 0; i < 4; i++) {
            startActivity(intent);
        }

        Log.d(LOG_TAG, "i am dead");
    }
}
