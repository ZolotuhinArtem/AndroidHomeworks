package com.example.arch.hw1_mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arch.hw1_mvvm.R;
import com.example.arch.hw1_mvvm.adapters.UserAdapter;
import com.example.arch.hw1_mvvm.databinding.ActivityMainBinding;
import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.repositories.UserRepositoryProvider;
import com.example.arch.hw1_mvvm.utils.UserTools;
import com.example.arch.hw1_mvvm.viewmodel.MainViewModel;

import java.util.List;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private MainViewModel viewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.viewModel = new MainViewModel(UserRepositoryProvider.getUserRepository());
        this.viewModel.addObserver(this);
        binding.setViewModel(viewModel);

        this.userAdapter = new UserAdapter(this);
        this.userAdapter.setList(this.viewModel.getUserList());
        binding.rvUsers.setAdapter(this.userAdapter);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goToEditActivity(User user) {
        Intent intent = new Intent(this, EditActivity.class);
        intent = UserTools.fillIntent(intent, user);
        startActivityForResult(intent, 0);
    }



    //K O C T bI JI b
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.update(null, null);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        this.userAdapter.setList(this.viewModel.getUserList());
    }
}
