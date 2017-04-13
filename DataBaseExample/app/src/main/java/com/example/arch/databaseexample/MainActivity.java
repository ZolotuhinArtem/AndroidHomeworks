package com.example.arch.databaseexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SparseArray<EditText> userInputs = new SparseArray<>();
    private Button btnConfirm;
    private UserDataBaseHelper userDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userInputs.put(0, (EditText) findViewById(R.id.et_name));
        this.userInputs.put(1, (EditText) findViewById(R.id.et_city));
        this.userInputs.put(2, (EditText) findViewById(R.id.et_age));
        this.userInputs.put(3, (EditText) findViewById(R.id.et_gender));

        this.btnConfirm = (Button) findViewById(R.id.btn_confirm);
        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(userInputs.get(0).getText().toString(),
                        userInputs.get(1).getText().toString(),
                        userInputs.get(2).getText().toString(),
                        userInputs.get(3).getText().toString());
            }
        });

        this.userDataBaseHelper = new UserDataBaseHelper(this);
        SQLiteDatabase db = userDataBaseHelper.getReadableDatabase();
        getUser("a");
    }

    public User getUser(String name) {
        String[] args = new String[1];
        args[0] = name;

        Cursor cursor = this.userDataBaseHelper.getReadableDatabase().query(
                UserContract.UserEntry.TABLE_NAME,
                null, UserContract.UserEntry.NAME + " = ?",
                args, null, null, null);

        int nameColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.NAME);
        int cityColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.CITY);
        int ageColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.AGE);
        int genderColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.GENDER);
        List<User> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User(
                    cursor.getString(nameColumnIndex),
                    cursor.getString(cityColumnIndex),
                    cursor.getInt(ageColumnIndex),
                    cursor.getInt(genderColumnIndex));
            list.add(user);
        }
        cursor.close();

        for(User i: list) {
            Log.d("debug", i.toString());
        }
        return null;
    }

    public void saveUser(String name, String city, String age, String gender) {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.NAME, name);
        values.put(UserContract.UserEntry.CITY, city);
        values.put(UserContract.UserEntry.AGE, age);
        values.put(UserContract.UserEntry.GENDER, gender);
        long id = this.userDataBaseHelper.getWritableDatabase().insert(UserContract.UserEntry.TABLE_NAME, null, values);
        Log.d("debug", Long.toString(id));
    }
}
