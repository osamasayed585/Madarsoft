package com.madarsoft.madarsoft.classes.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.madarsoft.madarsoft.R;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

/* if you need Validation for data (input) anything you can write the fun here and use it any class */
public class Validator {

    private final Context context;

    @Inject
    public Validator(@ActivityContext Context context) {
        this.context = context;
    }

    public boolean isNotEmpty(EditText view) {
        String value = view.getText().toString().trim();
        if (value == null || value.equals("")) {
            view.setError(getResources().getString(R.string.errorRequired));
            view.requestFocus();
            return false;
        }
        return true;
    }

    private Resources getResources() {
        return context.getResources();
    }

    public boolean checked(boolean checked, TextView view) {
        if (!checked) {
            view.setError(getResources().getString(R.string.errorRequired));
            view.requestFocus();
            return false;
        }
        return true;
    }

    public boolean isChecked(CheckBox cbTerms, String message) {
        if (!cbTerms.isChecked()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


}
