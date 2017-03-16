package com.example.arch.hw2_usersfromservice.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arch.hw2_usersfromservice.R;
import com.example.arch.hw2_usersfromservice.list.user.UserAdapter;
import com.example.arch.hw2_usersfromservice.list.user.UserListItemClickListener;
import com.example.arch.hw2_usersfromservice.model.User;
import com.example.arch.hw2_usersfromservice.presenter.UsersPresenter;
import com.example.arch.hw2_usersfromservice.util.UserTools;

import java.util.List;

public class UsersActivity extends AppCompatActivity implements UsersView, View.OnClickListener, UserListItemClickListener {

    private static final String ATTR_LOADING = "is-loading";

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private UsersPresenter presenter;
    private Button btnUpdate, btnClear;
    private ProgressBar progressBar;
    private View progressCover;

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);

        setSupportActionBar(toolbar);

        this.recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        this.btnUpdate = (Button) findViewById(R.id.btn_update);
        this.btnClear = (Button) findViewById(R.id.btn_clear);
        this.progressBar = (ProgressBar) findViewById(R.id.pb);
        this.progressCover = findViewById(R.id.progress_cover);
        this.adapter = new UserAdapter();

        this.adapter.setListener(this);
        this.btnUpdate.setOnClickListener(this);
        this.btnClear.setOnClickListener(this);

        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState != null) {
            loadSavedState(savedInstanceState);
        } else {
            this.setLoading(false);
        }

        this.presenter = new UsersPresenter(this, this);

    }

    private void loadSavedState(Bundle bundle) {
        this.setLoading(bundle.getBoolean(ATTR_LOADING));
    }


    @Override
    public void showUsers(List<User> users) {
        this.adapter.setList(users);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(ATTR_LOADING, this.isLoading);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void goToUserDetailView(User user) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        UserTools.fillIntent(intent, user);
        startActivity(intent);
    }

    @Override
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        this.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        this.progressCover.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        this.btnUpdate.setClickable(!isLoading);
        this.btnClear.setClickable(!isLoading);
        this.recyclerView.setClickable(!isLoading);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                this.presenter.onButtonUpdateClick();
                break;
            case R.id.btn_clear:
                this.presenter.onButtonClearClick();
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        this.presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onUserItemClick(User user) {
        if (!isLoading) {
            this.presenter.onItemUserClick(user);
        }
    }


}
