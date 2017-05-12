package com.example.arch.hw2_usersfromservice.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by arch on 3/14/17.
 */

public class UserRequestReciever extends BroadcastReceiver {

    private UserRequestRecieverListener listener;

    public UserRequestReciever(UserRequestRecieverListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.listener.onUserRequestRecieve(intent.getStringExtra(UserRequestService.ATTR_STATUS));
    }
}
