package com.example.arch.hw1_mvp.presenters;

import com.example.arch.hw1_mvp.repositories.UserRepository;
import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.utils.UserTools;
import com.example.arch.hw1_mvp.views.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arch on 06.03.17.
 */

public class MainPresenter {

    private UserRepository userRepository;
    private MainView mainView;

    public MainPresenter(MainView mainView, UserRepository userRepository) {
        this.mainView = mainView;
        this.userRepository = userRepository;
        this.mainView.setUserList(this.getUserList());
    }

    public List<User> getUserList(){
        List<User> list = new ArrayList<>();
        for(User i: this.userRepository.getAll()) {
            list.add(i);
        }
        return list;
    }

    public void userItemClick(User user) {
        this.mainView.goToEditActivity(user);
    }

    public void buttonGenerateClick(){
        this.userRepository.clear();
        this.userRepository.add(UserTools.generate(20));
        this.mainView.setUserList(this.getUserList());
    }
}
