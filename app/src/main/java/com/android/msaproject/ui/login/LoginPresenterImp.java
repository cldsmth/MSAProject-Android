package com.android.msaproject.ui.login;

import android.app.Activity;

import com.android.msaproject.api.UserAPI;
import com.android.msaproject.api.data.LoginData;
import com.android.msaproject.api.response.ObjectResponse;
import com.android.msaproject.model.Login;
import com.android.msaproject.service.Retrofit;
import com.android.msaproject.util.Utils;
import com.android.msaproject.util.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView mView;
    private Activity mActivity;

    public LoginPresenterImp(LoginView loginView, Activity activity) {
        this.mView = loginView;
        this.mActivity = activity;
    }

    @Override
    public void validate(Login login) {
        boolean valid = true;

        if (!Validation.validateFields(login.getUserId())) {
            valid = false;
            mView.onErrorEmptyUserId();
        } else if (!Validation.validateFields(login.getPassword())) {
            valid = false;
            mView.onErrorEmptyPassword();
        }

        mView.onValidate(valid);
    }

    @Override
    public void submit(Login login) {
        mView.onPreProcess();
        if(Utils.haveNetworkConnection(mActivity)){
            UserAPI service = Retrofit.setup().create(UserAPI.class);
            Call<ObjectResponse<LoginData>> call = service.login(login.getUserId(), login.getPassword());
            System.out.println("Call : " + call.request().url());
            call.enqueue(new Callback<ObjectResponse<LoginData>>() {
                @Override
                public void onResponse(Call<ObjectResponse<LoginData>> call, Response<ObjectResponse<LoginData>> response) {
                    System.out.println("Result : " + response.message());
                    String status = response.body().getStatus();
                    if(status.equals("200")){
                        LoginData data = response.body().getData();
                        mView.onSuccess(data);
                    }else{
                        mView.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<ObjectResponse<LoginData>> call, Throwable t) {
                    mView.onFailed();
                }
            });
        }else{
            mView.onOffline();
        }
    }

}