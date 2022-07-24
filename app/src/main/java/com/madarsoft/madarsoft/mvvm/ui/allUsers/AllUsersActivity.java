package com.madarsoft.madarsoft.mvvm.ui.allUsers;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.madarsoft.madarsoft.R;
import com.madarsoft.madarsoft.mvvm.base.BaseActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;
import lombok.Getter;

@AndroidEntryPoint
public class AllUsersActivity extends BaseActivity {

    AllUsersViewModel mViewModel;

    @Inject
    public AllUsersActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewModel Setup
        mViewModel = new ViewModelProvider(this).get(AllUsersViewModel.class);
        mViewModel.getOnLoadingMutableLiveData().observe(this, this::onLoading);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.requestUsers();
    }
}