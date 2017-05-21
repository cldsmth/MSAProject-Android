package com.android.msaproject.ui.dashboard;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.msaproject.R;
import com.android.msaproject.service.Preference;
import com.android.msaproject.ui.login.LoginActivity;
import com.android.msaproject.util.Const;
import com.android.msaproject.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private DashboardActivity _this = this;
    private Preference preference;
    private Preference.User userPreference;
    private ProgressDialog progress;

    private boolean isCheck = false;

    @Bind(R.id.tv_user)
    TextView tvUser;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.cardview_checkin)
    CardView cardViewCheckin;
    @Bind(R.id.cardview_about)
    CardView cardViewAbout;
    @Bind(R.id.cardview_setting)
    CardView cardViewSetting;
    @Bind(R.id.cardview_checkout)
    CardView cardViewCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(_this);
        preference = Preference.getInstance(_this);
        userPreference = preference.getObject(Const.PREFERENCE_KEY_USER, Preference.User.class);
        progress = new ProgressDialog(_this);

        tvUser.setText(userPreference.getFname());
        tvStatus.setText(String.format(
                getResources().getString(R.string.dashboard_text_status),
                "Saat ini anda belum melakukan check in")
        );
        cardViewCheckin.setOnClickListener(_this);
        cardViewAbout.setOnClickListener(_this);
        cardViewSetting.setOnClickListener(_this);
        cardViewCheckout.setOnClickListener(_this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview_checkin:
                if(isCheck){
                    Utils.displayToast(_this, "Anda sudah melakukan check in", Toast.LENGTH_SHORT);
                }else{
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setCancelable(true);
                    progress.setMessage("Check in..");
                    progress.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            isCheck = true;
                            tvStatus.setText(String.format(
                                    getResources().getString(R.string.dashboard_text_status),
                                    "Anda telah melakukan check in aplikasi")
                            );
                        }
                    }, 2000);
                }
                break;
            case R.id.cardview_about:
                Utils.displayToast(_this, "About", Toast.LENGTH_SHORT);
                break;
            case R.id.cardview_setting:
                Utils.displayToast(_this, "Setting", Toast.LENGTH_SHORT);
                break;
            case R.id.cardview_checkout:
                checkOutDialog();
                break;
        }
    }

    private void checkOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(_this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah anda ingin melakukan check out aplikasi ?");

        String positiveText = "Ya";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preference.clearObject();
                        Utils.intent(_this, LoginActivity.class);
                        _this.finish();
                    }
                });

        String negativeText = "Tidak";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show(); // display dialog
    }

}