package com.example.arch.hw1_mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arch.hw1_mvvm.R;
import com.example.arch.hw1_mvvm.databinding.ActivityEditBinding;
import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.repositories.UserRepositoryProvider;
import com.example.arch.hw1_mvvm.utils.UserTools;
import com.example.arch.hw1_mvvm.viewmodel.EditViewModel;

import java.util.Observable;
import java.util.Observer;

public class EditActivity extends AppCompatActivity implements Observer{

    // K O S T bI JI b
    private User user;

    private EditViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // K O S T bI JI b
        if (savedInstanceState == null) {
            this.user = UserTools.getFromIntent(getIntent());
        } else {
            this.user = this.loadUser(savedInstanceState);
        }

        ActivityEditBinding activityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit);

        this.viewModel = new EditViewModel(this.user, activityEditBinding, UserRepositoryProvider.getUserRepository());
        this.viewModel.addObserver(this);
        activityEditBinding.setViewModel(this.viewModel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        this.saveUser(this.user, outState);
        super.onSaveInstanceState(outState);
    }

    private void saveUser(User user, Bundle bundle) {
        UserTools.fillBundle(bundle, user);
    }

    private User loadUser(Bundle savedInstanceState) {
        return UserTools.getFromBundle(savedInstanceState);
    }

    @Override
    public void update(Observable o, Object arg) {
        // K O S T bI JI b
        finish();
    }
}
