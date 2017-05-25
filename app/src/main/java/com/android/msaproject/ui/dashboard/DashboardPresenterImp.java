package com.android.msaproject.ui.dashboard;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

public class DashboardPresenterImp implements DashboardPresenter {

    private DashboardView mView;
    private Activity mActivity;

    public DashboardPresenterImp(DashboardView dashboardView, Activity activity) {
        this.mView = dashboardView;
        this.mActivity = activity;
    }

    @Override
    public void checkin(boolean isCheckin) {
        if(isCheckin){
            mView.onCheckinExist();
        }else{
            mView.onPreCheckIn();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mView.onCheckin();
                }
            }, 2000);
        }
    }

    @Override
    public void checkout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah anda ingin melakukan check out aplikasi ?");

        String positiveText = "Ya";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mView.onCheckout();
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