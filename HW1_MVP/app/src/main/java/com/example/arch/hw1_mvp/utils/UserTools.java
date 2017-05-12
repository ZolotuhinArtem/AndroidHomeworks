package com.example.arch.hw1_mvp.utils;

import android.content.Intent;
import android.os.Bundle;

import com.example.arch.hw1_mvp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by arch on 06.03.17.
 */

public class UserTools {

    private final static String[] fnames = {"John", "Thomes", "Bill", "Max", "Quentin", "Bruce", "Jason", "Boris", "Viktor", "Jora"};
    private final static String[] snames = {"Ivanov", "Smith", "Petrov", "Sidorov", "Grubov", "Portov", "Bookov", "Dostoevskij", "Pushkin", "Lermontov"};

    public static Intent fillIntent(Intent intent, User user) {
        intent.putExtra(User.ATTRIBUTE_FIRST_NAME, user.getFirstName());
        intent.putExtra(User.ATTRIBUTE_SECOND_NAME, user.getSecondName());
        intent.putExtra(User.ATTRIBUTE_ADDRESS, user.getAddress());
        return intent;
    }

    public static User getFromIntent(Intent intent) {
        return new User(intent.getStringExtra(User.ATTRIBUTE_FIRST_NAME),
                intent.getStringExtra(User.ATTRIBUTE_SECOND_NAME),
                intent.getStringExtra(User.ATTRIBUTE_ADDRESS));
    }

    public static Bundle fillBundle(Bundle bundle, User user) {
        bundle.putString(User.ATTRIBUTE_FIRST_NAME, user.getFirstName());
        bundle.putString(User.ATTRIBUTE_SECOND_NAME, user.getSecondName());
        bundle.putString(User.ATTRIBUTE_ADDRESS, user.getAddress());
        return bundle;
    }

    public static User getFromBundle(Bundle bundle) {
        return new User(bundle.getString(User.ATTRIBUTE_FIRST_NAME),
                bundle.getString(User.ATTRIBUTE_SECOND_NAME),
                bundle.getString(User.ATTRIBUTE_ADDRESS));
    }

    // WORK WITH JSON

    public static JSONObject fillJson(JSONObject jobject, User user) {
        try {
            jobject.put(User.ATTRIBUTE_FIRST_NAME, user.getFirstName());
            jobject.put(User.ATTRIBUTE_SECOND_NAME, user.getSecondName());
            jobject.put(User.ATTRIBUTE_ADDRESS, user.getAddress());
            return jobject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getFromJson(JSONObject jobject) {
        try {
            String firstName = jobject.getString(User.ATTRIBUTE_FIRST_NAME);
            String secondName = jobject.getString(User.ATTRIBUTE_SECOND_NAME);
            String address = jobject.getString(User.ATTRIBUTE_ADDRESS);
            if ((firstName != null) && (secondName != null) && (address != null)) {
                return  new User(firstName, secondName, address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User generate(){
        String fname = fnames[(int) Math.round(Math.random() * (fnames.length - 1))];
        String sname = snames[(int) Math.round(Math.random() * (snames.length - 1))];
        String address = Double.toString(Math.random() * 1000);
        return new User(fname, sname, address);
    }

    public static Set<User> generate(int count){
        Set<User> set = new HashSet<>();
        for(int i = 0; i < count; i++){
            set.add(generate());
        }
        return set;
    }
}
