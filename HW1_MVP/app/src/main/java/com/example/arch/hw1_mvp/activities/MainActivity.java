package com.example.arch.hw1_mvp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.arch.hw1_mvp.R;
import com.example.arch.hw1_mvp.adapters.UserAdapter;
import com.example.arch.hw1_mvp.repositories.JsonSharedPreferencesUserRepository;
import com.example.arch.hw1_mvp.repositories.UserRepository;
import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.repositories.UserRepositoryProvider;
import com.example.arch.hw1_mvp.utils.UserTools;
import com.example.arch.hw1_mvp.presenters.MainPresenter;
import com.example.arch.hw1_mvp.views.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        this.userAdapter = new UserAdapter();
        recyclerView.setAdapter(this.userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Creating mainPresentor after creating adapter only
        this.mainPresenter = new MainPresenter(this, UserRepositoryProvider.getUserRepository());

        this.userAdapter.setListener(new UserAdapter.UserClickListener() {
            @Override
            public void onUserClick(User user) {
                mainPresenter.userItemClick(user);
            }
        });

        Button btnGenerate = (Button) findViewById(R.id.btn_generate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.buttonGenerateClick();
            }
        });
    }

    @Override
    public void goToEditActivity(User user) {
        Intent intent = new Intent(this, EditActivity.class);
        intent = UserTools.fillIntent(intent, user);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setUserList(this.mainPresenter.getUserList());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setUserList(List<User> list) {
        this.userAdapter.setList(list);
    }
}
