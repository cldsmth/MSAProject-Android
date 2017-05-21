package com.android.msaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.msaproject.service.Preference;
import com.android.msaproject.ui.dashboard.DashboardActivity;
import com.android.msaproject.ui.login.LoginActivity;
import com.android.msaproject.util.Const;
import com.android.msaproject.util.Utils;

public class MainActivity extends AppCompatActivity {

    private MainActivity _this = this;
    private Preference preference;
    private Preference.User userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = Preference.getInstance(_this);
        userPreference = preference.getObject(Const.PREFERENCE_KEY_USER, Preference.User.class);

        if(userPreference != null){
            Utils.intent(_this, DashboardActivity.class);
        }else {
            Utils.intent(_this, LoginActivity.class);
        }
        _this.finish();
    }

}