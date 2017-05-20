package com.android.msaproject.ui.login;

import android.app.Activity;
import android.widget.Toast;

import com.android.msaproject.util.Utils;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView mView;
    private Activity mActivity;

    public LoginPresenterImp(LoginView loginView, Activity activity) {
        this.mView = loginView;
        this.mActivity = activity;
    }

    @Override
    public void validate(LoginModel model) {
        boolean valid = true;

        if (model.getUserId().isEmpty()) {
            valid = false;
            mView.onErrorEmptyUserId();
        } else if (model.getPassword().isEmpty()) {
            valid = false;
            mView.onErrorEmptyPassword();
        }

        mView.onValidate(valid);
    }

    @Override
    public void submit(LoginModel model) {
        System.out.println("======================");
        System.out.println("User Id : " + model.getUserId());
        System.out.println("Password : " + model.getPassword());

        Utils.displayToast(mActivity, "Submit", Toast.LENGTH_SHORT);
        if(model.getUserId().equals("123456") && model.getPassword().equals("password")){
            mView.onSuccess();
        }else{
            mView.onFailed();
        }
    }

}