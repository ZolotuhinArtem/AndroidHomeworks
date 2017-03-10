package com.example.arch.hw1_mvp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arch.hw1_mvp.R;
import com.example.arch.hw1_mvp.repositories.JsonSharedPreferencesUserRepository;
import com.example.arch.hw1_mvp.repositories.UserRepository;
import com.example.arch.hw1_mvp.model.User;
import com.example.arch.hw1_mvp.repositories.UserRepositoryProvider;
import com.example.arch.hw1_mvp.utils.UserTools;
import com.example.arch.hw1_mvp.presenters.EditPresenter;
import com.example.arch.hw1_mvp.views.EditView;

public class EditActivity extends AppCompatActivity implements EditView {

    private EditText etFirstName, etSecondName, etAddress;
    private Button btnSave;
    private EditPresenter editPresenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (savedInstanceState == null) {
            this.user = UserTools.getFromIntent(getIntent());
        } else {
            this.user = loadUser(savedInstanceState);
        }

        this.etFirstName = (EditText) findViewById(R.id.et_fname);
        this.etSecondName = (EditText) findViewById(R.id.et_sname);
        this.etAddress = (EditText) findViewById(R.id.et_address);

        this.btnSave = (Button) findViewById(R.id.btn_save);
        this.editPresenter = new EditPresenter(this, UserRepositoryProvider.getUserRepository());

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.saveClick();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        this.saveUser(this.getUser(), outState);
        super.onSaveInstanceState(outState);
    }

    private void saveUser(User user, Bundle bundle) {
        UserTools.fillBundle(bundle, user);
    }

    private User loadUser(Bundle savedInstanceState) {
        return UserTools.getFromBundle(savedInstanceState);
    }

    @Override
    public String getFirstName() {
        return this.etFirstName.getText().toString();
    }

    @Override
    public String getSecondName() {
        return this.etSecondName.getText().toString();
    }

    @Override
    public String getAddress() {
        return this.etAddress.getText().toString();
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void showUser(User user) {
        this.etFirstName.setText(user.getFirstName());
        this.etSecondName.setText(user.getSecondName());
        this.etAddress.setText(user.getAddress());
    }

    @Override
    public void exit() {
        finish();
    }

}
