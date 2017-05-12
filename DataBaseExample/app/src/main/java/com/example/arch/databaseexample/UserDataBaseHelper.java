package com.example.arch.databaseexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by arch on 3/18/17.
 */

public class UserDataBaseHelper extends SQLiteOpenHelper {

    public static final String DATA_BASE_NAME = "User.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String DATA_BASE_USER_TABLE_NAME = "users";

    public UserDataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command = "CREATE TABLE " + DATA_BASE_USER_TABLE_NAME + "("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.NAME + " TEXT NOT NULL, "
                + UserContract.UserEntry.CITY + " TEXT NOT NULL, "
                + UserContract.UserEntry.AGE + " INTEGER NOT NULL,"
                + UserContract.UserEntry.GENDER + " INTEGER DEFAULT "
                + UserContract.UserEntry.MALE + ");";
        Log.d("debug", command);
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
