package com.android.msaproject.ui.login;

import com.android.msaproject.api.data.LoginData;

public interface LoginView {
    /**
     * Validate.
     */
    void onValidate(boolean valid);
    /**
     * Pre process
     */
    void onPreProcess();
    /**
     * Success.
     */
    void onSuccess(LoginData data);
    /**
     * Failed.
     */
    void onFailed();
    /**
     * Offline.
     */
    void onOffline();
    /**
     * Error user id empty.
     */
    void onErrorEmptyUserId();
    /**
     * Error password empty.
     */
    void onErrorEmptyPassword();
}