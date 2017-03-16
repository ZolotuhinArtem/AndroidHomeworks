package com.example.arch.hw2_usersfromservice.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.arch.hw2_usersfromservice.R;
import com.example.arch.hw2_usersfromservice.model.User;
import com.example.arch.hw2_usersfromservice.presenter.UserDetailPresenter;
import com.example.arch.hw2_usersfromservice.util.UserTools;

public class UserDetailActivity extends AppCompatActivity implements UserDetailView {

    private TextView
        tvFirstName,
        tvLastName,
        tvEmail,
        tvAddress,
        tvCreated,
        tvBalance;

    private UserDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        this.tvFirstName = (TextView) findViewById(R.id.tv_fname);
        this.tvLastName = (TextView) findViewById(R.id.tv_lname);
        this.tvEmail = (TextView) findViewById(R.id.tv_email);
        this.tvAddress = (TextView) findViewById(R.id.tv_address);
        this.tvCreated = (TextView) findViewById(R.id.tv_created);
        this.tvBalance = (TextView) findViewById(R.id.tv_balance);

        this.presenter = new UserDetailPresenter(this);
    }

    @Override
    public User getUser() {
        return UserTools.getFromIntent(getIntent());
    }

    @Override
    public void showUser(@NonNull User user) {
        this.tvFirstName.setText(user.getFirstName());
        this.tvLastName.setText(user.getLastName());
        this.tvEmail.setText(user.getEmail());
        this.tvAddress.setText(user.getAddress());
        this.tvCreated.setText(user.getCreated());
        this.tvBalance.setText(user.getBalance());
    }
}
