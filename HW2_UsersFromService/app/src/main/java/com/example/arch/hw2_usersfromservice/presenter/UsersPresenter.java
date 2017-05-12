package com.example.arch.hw2_usersfromservice.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import com.example.arch.hw2_usersfromservice.model.User;
import com.example.arch.hw2_usersfromservice.repository.JsonSharedPreferencesUserRepository;
import com.example.arch.hw2_usersfromservice.repository.UserRepository;
import com.example.arch.hw2_usersfromservice.service.UserRequestReciever;
import com.example.arch.hw2_usersfromservice.service.UserRequestRecieverListener;
import com.example.arch.hw2_usersfromservice.service.UserRequestService;
import com.example.arch.hw2_usersfromservice.util.CollectionTools;
import com.example.arch.hw2_usersfromservice.view.UsersView;

/**
 * Created by arch on 3/14/17.
 */

public class UsersPresenter implements UserRequestRecieverListener {

    private Context context;
    private UserRequestReciever reciever;
    private UserRepository userRepository;
    private UsersView view;

    public UsersPresenter(Context context, UsersView view) {
        this.context = context;
        this.view = view;
        IntentFilter intentFilter = new IntentFilter(UserRequestService.USER_RESPONSE_ACTION);
        this.reciever = new UserRequestReciever(this);
        this.context.registerReceiver(this.reciever, intentFilter);

        SharedPreferences sharedPreferences = this.context.getSharedPreferences(
                JsonSharedPreferencesUserRepository.DEFAULT_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.userRepository = new JsonSharedPreferencesUserRepository(sharedPreferences,
                JsonSharedPreferencesUserRepository.DEFAULT_KEY);

        showUsers();
    }

    public void onItemUserClick(User user) {
        this.view.goToUserDetailView(user);
    }

    public void onButtonUpdateClick(){
        Intent intent = new Intent(this.context, UserRequestService.class);
        this.context.startService(intent);
        this.view.setLoading(true);

    }

    public void onButtonClearClick(){
        this.userRepository.clear();
        this.showUsers();
    }

    public void destroy(){
      this.context.unregisterReceiver(this.reciever);
    }

    private void showUsers(){
        this.view.showUsers(CollectionTools.changeSetToList(this.userRepository.getAll()));
    }

    @Override
    public void onUserRequestRecieve(String status) {
        this.view.setLoading(false);
        if (status.equals(UserRequestService.PARAM_STATUS_OK)) {
            showUsers();
            view.showMessage("Updated!");
        } else {
            view.showError("Error to get users!");
        }
    }
}
