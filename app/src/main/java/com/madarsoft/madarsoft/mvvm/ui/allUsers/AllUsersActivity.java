package com.madarsoft.madarsoft.mvvm.ui.allUsers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.madarsoft.madarsoft.R;
import com.madarsoft.madarsoft.classes.adapters.UsersAdapter;
import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.databinding.ActivityUsersBinding;
import com.madarsoft.madarsoft.mvvm.base.BaseActivity;
import com.madarsoft.madarsoft.mvvm.ui.addUser.AddUserActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class AllUsersActivity extends BaseActivity {

    private AllUsersViewModel mViewModel;
    private ActivityUsersBinding mBinding;

    @Inject
    UsersAdapter mUsersAdapter;

    @Inject
    public AllUsersActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //ViewModel Setup
        mViewModel = new ViewModelProvider(this).get(AllUsersViewModel.class);
        mViewModel.getOnLoadingMutableLiveData().observe(this, this::onLoading);
        mViewModel.getRequestUserResponseMutableLiveData().observe(this, this::onFetchUsers);


        mBinding.btnAddUser.setOnClickListener(this::onAddUser);

        // setUp list of Users
        mBinding.users.setAdapter(mUsersAdapter);
        mUsersAdapter.initItemClickListener(this::onUserClicked);


    }

    private void onUserClicked(User user) {
        Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_SHORT).show();
    }

    private void onFetchUsers(List<User> users) {
        mUsersAdapter.setData(users);
    }

    private void onAddUser(View view) {
        startActivity(new Intent(this, AddUserActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.requestUsers();
    }
}