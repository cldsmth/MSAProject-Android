package com.android.msaproject.ui.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.msaproject.R;
import com.android.msaproject.service.Preference;
import com.android.msaproject.ui.login.LoginActivity;
import com.android.msaproject.util.Const;
import com.android.msaproject.util.Utils;

public class DashboardActivity extends AppCompatActivity {

    private DashboardActivity _this = this;
    private Preference preference;
    private Preference.User userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        preference = Preference.getInstance(_this);
        userPreference = preference.getObject(Const.PREFERENCE_KEY_USER, Preference.User.class);
        System.out.println("========================");
        System.out.println("User Id : " + userPreference.getUserId());
        System.out.println("First Name : " + userPreference.getFname());
        System.out.println("Last Name : " + userPreference.getLname());
        System.out.println("Phone : " + userPreference.getPhone());
        System.out.println("Auth Code : " + userPreference.getAuthCode());
        Utils.displayToast(_this, "Hello " + userPreference.getFname(), Toast.LENGTH_SHORT);

        Button btnCheckout = (Button) findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.clearObject();
                Utils.intent(_this, LoginActivity.class);
                _this.finish();
            }
        });
    }

}