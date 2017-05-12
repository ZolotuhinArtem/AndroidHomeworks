package com.example.arch.hw1_mvvm.viewmodel;

import com.example.arch.hw1_mvvm.model.User;
import com.example.arch.hw1_mvvm.repositories.UserRepository;
import com.example.arch.hw1_mvvm.repositories.UserRepositoryProvider;
import com.example.arch.hw1_mvvm.utils.CollectionsTools;
import com.example.arch.hw1_mvvm.utils.UserTools;

import java.util.List;
import java.util.Observable;

/**
 * Created by arch on 3/8/17.
 */

public class MainViewModel extends Observable{

    private List<User> userList;
    private UserRepository userRepository;

    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<User> list = CollectionsTools.setToList(this.userRepository.getAll());
        this.setUserList(list);
    }

    public void onGenerateClick(){
        this.userRepository.clear();
        this.userRepository.add(UserTools.generate(20));
        List<User> list = CollectionsTools.setToList(this.userRepository.getAll());
        this.setUserList(list);
    }

    private void setUserList(List<User> list) {
        this.userList = list;
        setChanged();
        notifyObservers();
    }

    public List<User> getUserList() {
        this.userList = CollectionsTools.setToList(this.userRepository.getAll());
        return this.userList;
    }
}
