package com.madarsoft.madarsoft.mvvm.ui.addUser;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.madarsoft.madarsoft.R;
import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.databinding.ActivityAddUserBinding;
import com.madarsoft.madarsoft.mvvm.base.BaseActivity;
import com.madarsoft.madarsoft.mvvm.ui.allUsers.AllUsersViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddUserActivity extends BaseActivity {

    private ActivityAddUserBinding mBinding;
    private AddUserViewModel mViewModel;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        // setUp view model
        mViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
        mViewModel.getSaveUserResponseMutableLiveData().observe(this, this::onAddUserResponse);

        mBinding.btnSave.setOnClickListener(this::onSaveUser);
    }

    private void onAddUserResponse(Long aLong) {
        clearFields();
    }

    private void clearFields() {
        mBinding.txvName.setText("");
        mBinding.txvAge.setText("");
        mBinding.txvJob.setText("");
    }

    private void onSaveUser(View view) {

        if (!isValidation())
            return;

        mUser = new User(
                Objects.requireNonNull(mBinding.txvName.getText()).toString(),
                Integer.parseInt(Objects.requireNonNull(mBinding.txvAge.getText()).toString()),
                Objects.requireNonNull(mBinding.txvJob.getText()).toString(),
                initGender()
        );

        mViewModel.requestAddToCart(mUser);
    }

    private String initGender() {
        if (mBinding.rdoMale.isChecked())
            return getResources().getString(R.string.male);
        else
            return getResources().getString(R.string.female);
    }

    private boolean isValidation() {
        return getValidator().isNotEmpty(mBinding.txvName) &&
                getValidator().isNotEmpty(mBinding.txvAge) &&
                getValidator().isNotEmpty(mBinding.txvJob);
    }
}