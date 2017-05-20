package com.android.msaproject.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static String textInput(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static void intent(Context context, Class<?> classs) {
        Intent intent = new Intent(context, classs);
        context.startActivity(intent);
    }

    public static void displayToast(Context context, String message, int toast) {
        Toast.makeText(context, message, toast).show();
    }

}