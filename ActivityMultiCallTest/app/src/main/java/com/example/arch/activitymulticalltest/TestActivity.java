package com.example.arch.activitymulticalltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {


    public static final String LOG_TAG = "test-activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String value = Integer.toString((int)Math.round(Math.random() * 100));
        Log.d(LOG_TAG, value);
        ((TextView) findViewById(R.id.tv_number)).setText(value);
    }
}
