package com.example.arch.hw2_usersfromservice.util;

import android.content.Intent;
import android.os.Bundle;

import com.example.arch.hw2_usersfromservice.model.User;

import org.json.JSONException;
import org.json.JSONObject;


public class UserTools {

    public static final String ATTR_FIRST_NAME = "user-first-name";
    public static final String ATTR_LAST_NAME = "user-last-name";
    public static final String ATTR_EMAIL = "user-email";
    public static final String ATTR_ADDRESS = "user-address";
    public static final String ATTR_CREATED = "user-created";
    public static final String ATTR_BALANCE = "user-balance";

    public static Intent fillIntent(Intent intent, User user) {
        intent.putExtra(ATTR_FIRST_NAME, user.getFirstName());
        intent.putExtra(ATTR_LAST_NAME, user.getLastName());
        intent.putExtra(ATTR_EMAIL, user.getEmail());
        intent.putExtra(ATTR_ADDRESS, user.getAddress());
        intent.putExtra(ATTR_CREATED, user.getCreated());
        intent.putExtra(ATTR_BALANCE, user.getBalance());
        return intent;
    }

    public static User getFromIntent(Intent i) {
        return new User(i.getStringExtra(ATTR_FIRST_NAME),
                i.getStringExtra(ATTR_LAST_NAME),
                i.getStringExtra(ATTR_EMAIL),
                i.getStringExtra(ATTR_ADDRESS),
                i.getStringExtra(ATTR_CREATED),
                i.getStringExtra(ATTR_BALANCE));
    }

    public static Bundle fillBundle(Bundle bundle, User user) {
        bundle.putString(ATTR_FIRST_NAME, user.getFirstName());
        bundle.putString(ATTR_LAST_NAME, user.getLastName());
        bundle.putString(ATTR_EMAIL, user.getEmail());
        bundle.putString(ATTR_ADDRESS, user.getAddress());
        bundle.putString(ATTR_CREATED, user.getCreated());
        bundle.putString(ATTR_BALANCE, user.getBalance());
        return bundle;
    }

    public static User getFromBundle(Bundle b) {
        return new User(b.getString(ATTR_FIRST_NAME),
                b.getString(ATTR_LAST_NAME),
                b.getString(ATTR_EMAIL),
                b.getString(ATTR_ADDRESS),
                b.getString(ATTR_CREATED),
                b.getString(ATTR_BALANCE));
    }

    // WORK WITH JSON

    public static JSONObject fillJson(JSONObject jobject, User user) {
        try {
            jobject.put(ATTR_FIRST_NAME, user.getFirstName());
            jobject.put(ATTR_LAST_NAME, user.getLastName());
            jobject.put(ATTR_EMAIL, user.getEmail());
            jobject.put(ATTR_ADDRESS, user.getAddress());
            jobject.put(ATTR_CREATED, user.getCreated());
            jobject.put(ATTR_BALANCE, user.getBalance());

            return jobject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getFromJson(JSONObject j) {
        try {
            return  new User(
                    j.getString(ATTR_FIRST_NAME),
                    j.getString(ATTR_LAST_NAME),
                    j.getString(ATTR_EMAIL),
                    j.getString(ATTR_ADDRESS),
                    j.getString(ATTR_CREATED),
                    j.getString(ATTR_BALANCE));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
