package com.example.arch.hw1_mvp.repositories;

import android.content.SharedPreferences;

import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.utils.UserTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by arch on 3/7/17.
 */

public class JsonSharedPreferencesUserRepository implements UserRepository {



    private SharedPreferences sharedPreferences;
    private String key;

    public JsonSharedPreferencesUserRepository(SharedPreferences sharedPreferences, String key) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
    }

    @Override
    public  Set<User> getAll() {
        Set<User> set = null;
        try {
            JSONArray jarray = new JSONArray(this.sharedPreferences.getString(this.key, "[]"));
            set = new HashSet<>(jarray.length());
            for (int i = 0; i < jarray.length(); i++) {
                User user = UserTools.getFromJson((JSONObject) jarray.get(i));
                if (user != null) {
                    set.add(user);
                }
            }
            return set;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        boolean result = false;

        if (user != null) {
            Set<User> set = getAll();

            if (set == null) {
                set = new HashSet<>(1);
            }
            set.add(user);

            JSONArray jarray = new JSONArray();
            jarray = fillJsonArray(jarray, set);


            saveJSONArray(jarray);
        }
    }

    private void saveJSONArray(JSONArray jarray) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(this.key, jarray.toString());
        editor.commit();
    }

    @Override
    public void add(Set<User> set) {
        Set<User> oldList = getAll();
        if (oldList == null) {
            oldList = set;
        } else {
            oldList.addAll(set);
        }

        JSONArray jarray = new JSONArray();
        jarray = fillJsonArray(jarray, set);

        saveJSONArray(jarray);
    }

    @Override
    public void clear() {
        saveJSONArray(new JSONArray());
    }


    @Override
    public  void delete(User user) {
        boolean result = false;
        Set<User> set = this.getAll();
        result = set.remove(user);
        if (result) {
            this.clear();
            this.add(set);
        }
    }

    private JSONArray fillJsonArray(JSONArray jarray, Set<User> set) {
        if (jarray == null) {
            jarray = new JSONArray();
        }
        if (set != null){
            for (User i : set) {
                if (i != null) {
                    JSONObject jobject = new JSONObject();
                    jobject = UserTools.fillJson(jobject, i);
                    if (jobject != null) {
                        jarray.put(jobject);
                    }
                }
            }
        }

        return jarray;
    }


}
