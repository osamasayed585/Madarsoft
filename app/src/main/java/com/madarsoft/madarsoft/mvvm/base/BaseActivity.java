package com.madarsoft.madarsoft.mvvm.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.SpinKitView;
import com.madarsoft.madarsoft.classes.utils.BaseResponse;
import com.madarsoft.madarsoft.classes.utils.Validator;
import com.madarsoft.madarsoft.databinding.ActivityBaseBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.Getter;

@Getter
@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Inject
    public Validator validator;
    public SpinKitView progressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        ActivityBaseBinding binding = ActivityBaseBinding.inflate(getLayoutInflater());
        progressView = binding.progressView;
        binding.fContainer.addView(view);
        super.setContentView(binding.getRoot());
    }

    public void onLoading(boolean isLoading) {
        if (isLoading) {
            if (progressView.getVisibility() != View.VISIBLE)
                progressView.setVisibility(View.VISIBLE);
        } else
            progressView.setVisibility(View.GONE);
    }


    public void onBackClick(View view) {
        finish();
    }
}
