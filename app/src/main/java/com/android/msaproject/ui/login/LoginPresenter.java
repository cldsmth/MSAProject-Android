package com.android.msaproject.ui.login;

import com.android.msaproject.model.Login;

public interface LoginPresenter {
    /**
     * Validate
     */
    void validate(Login login);
    /**
     * Submit
     */
    void submit(Login login);
}