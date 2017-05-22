package com.android.msaproject.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.msaproject.util.Const;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Preference {

    private static Preference preference;
    private static Gson GSON = new Gson();
    Type typeOfObject = new TypeToken<Object>() {
    }.getType();
    private SharedPreferences sharedPreferences;

    public static Preference getInstance(Context context) {
        if (preference == null) {
            preference = new Preference(context);
        }
        return preference;
    }

    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences(Const.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void clearObject() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is null");
        }
        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("Key is empty or null");
        }
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, GSON.toJson(object));
        prefsEditor.commit();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = sharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }

    public class User {

        private String userId;
        private String fname;
        private String lname;
        private String phone;
        private String authCode;
        private boolean isCheckIn;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public boolean isCheckIn() {
            return isCheckIn;
        }

        public void setCheckIn(boolean checkIn) {
            isCheckIn = checkIn;
        }

    }

}