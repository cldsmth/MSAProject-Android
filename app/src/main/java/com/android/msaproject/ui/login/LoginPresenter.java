package com.android.msaproject.ui.login;

public interface LoginPresenter {
    /**
     * Validate
     */
    void validate(LoginModel model);
    /**
     * Submit
     */
    void submit(LoginModel model);
}