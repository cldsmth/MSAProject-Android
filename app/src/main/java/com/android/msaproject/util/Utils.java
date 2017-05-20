package com.android.msaproject.util;

import android.content.Context;
import android.content.Intent;

public class Utils {

    public static void intent(Context context, Class<?> classs) {
        Intent intent = new Intent(context, classs);
        context.startActivity(intent);
    }

}