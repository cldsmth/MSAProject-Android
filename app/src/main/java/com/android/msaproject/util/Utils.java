package com.android.msaproject.util;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {

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