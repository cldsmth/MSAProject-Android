package com.android.msaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.msaproject.ui.login.LoginActivity;
import com.android.msaproject.util.Utils;

public class MainActivity extends AppCompatActivity {

    private MainActivity _this = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.intent(_this, LoginActivity.class);
        _this.finish();
    }

}