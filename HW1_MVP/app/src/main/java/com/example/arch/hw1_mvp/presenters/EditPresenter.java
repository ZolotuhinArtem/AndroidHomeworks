package com.example.arch.hw1_mvp.presenters;

import com.example.arch.hw1_mvp.repositories.UserRepository;
import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.views.EditView;

/**
 * Created by arch on 06.03.17.
 */

public class EditPresenter {

    private UserRepository userRepository;
    private EditView editView;

    public EditPresenter(EditView editView, UserRepository userRepository) {
        this.editView = editView;
        this.userRepository = userRepository;
        this.editView.showUser(this.editView.getUser());
    }

    public void saveClick(){
        User oldUser = this.editView.getUser();
        this.userRepository.delete(oldUser);
        User newUser = new User(this.editView.getFirstName(),
                this.editView.getSecondName(),
                this.editView.getAddress());
        this.userRepository.add(newUser);
        this.editView.setUser(newUser);
        this.editView.exit();
    }
}
