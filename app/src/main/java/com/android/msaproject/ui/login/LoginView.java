package com.android.msaproject.ui.login;

public interface LoginView {
    /**
     * Validate.
     */
    void onValidate(boolean valid);
    /**
     * Success.
     */
    void onSuccess();
    /**
     * Failed.
     */
    void onFailed();
    /**
     * Error user id empty.
     */
    void onErrorEmptyUserId();
    /**
     * Error password empty.
     */
    void onErrorEmptyPassword();
}