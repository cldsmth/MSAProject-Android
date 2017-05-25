package com.android.msaproject.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.msaproject.R;
import com.android.msaproject.service.Preference;
import com.android.msaproject.ui.login.LoginActivity;
import com.android.msaproject.util.Const;
import com.android.msaproject.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity implements DashboardView {

    private DashboardActivity _this = this;
    private DashboardPresenter presenter;
    private Preference preference;
    private Preference.User userPreference;
    private ProgressDialog progress;

    @Bind(R.id.tv_user) TextView tvUser;
    @Bind(R.id.tv_status) TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(_this);
        presenter = new DashboardPresenterImp(this, _this);
        preference = Preference.getInstance(_this);
        userPreference = preference.getObject(Const.PREFERENCE_KEY_USER, Preference.User.class);
        progress = new ProgressDialog(_this);

        tvUser.setText(userPreference.getName());
        tvStatus.setText(String.format(
                getResources().getString(R.string.dashboard_text_status),
                userPreference.isCheckIn() ? "Anda telah melakukan check in aplikasi"
                        : "Saat ini anda belum melakukan check in")
        );
    }

    @OnClick(R.id.cardview_checkin)
    public void checkin() {
        presenter.checkin(userPreference.isCheckIn());
    }

    @OnClick(R.id.cardview_about)
    public void about() {
        Utils.displayToast(_this, "About", Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.cardview_setting)
    public void setting() {
        Utils.displayToast(_this, "Setting", Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.cardview_checkout)
    public void checkout() {
        presenter.checkout();
    }

    @Override
    public void onPreCheckIn() {
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        progress.setMessage("Check in..");
        progress.show();
    }

    @Override
    public void onCheckin() {
        progress.dismiss();
        userPreference.setCheckIn(true);
        preference.putObject(Const.PREFERENCE_KEY_USER, userPreference);
        tvStatus.setText(String.format(
                getResources().getString(R.string.dashboard_text_status),
                "Anda telah melakukan check in aplikasi")
        );
    }

    @Override
    public void onCheckinExist() {
        Utils.displayToast(_this, "Anda sudah melakukan check in", Toast.LENGTH_SHORT);
    }

    @Override
    public void onCheckout() {
        preference.clearObject();
        Utils.intent(_this, LoginActivity.class);
        _this.finish();
    }

}