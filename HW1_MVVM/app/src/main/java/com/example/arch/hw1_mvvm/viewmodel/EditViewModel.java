package com.example.arch.hw1_mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.arch.hw1_mvvm.databinding.ActivityEditBinding;
import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.repositories.UserRepository;

import java.util.Observable;

/**
 * Created by arch on 3/9/17.
 */

public class EditViewModel extends Observable{

    private UserRepository userRepository;
    private ActivityEditBinding activityEditBinding;
    private User oldUser;
    public User user;


    public EditViewModel(User user, ActivityEditBinding activityEditBinding, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.activityEditBinding = activityEditBinding;
        this.oldUser = user;
        this.user = oldUser;
    }


    public void saveClick(){
        this.user = new User(activityEditBinding.etFname.getText().toString(),
                activityEditBinding.etSname.getText().toString(),
                activityEditBinding.etAddress.getText().toString());
        this.userRepository.delete(this.oldUser);
        this.userRepository.add(this.user);
        this.oldUser = this.user;
        setChanged();
        notifyObservers();
    }

    public User getUser() {
        return user;
    }
}
