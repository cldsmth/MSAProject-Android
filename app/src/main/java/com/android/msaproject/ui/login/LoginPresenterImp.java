package com.android.msaproject.ui.login;

import android.app.Activity;

import com.android.msaproject.api.UserAPI;
import com.android.msaproject.api.data.LoginData;
import com.android.msaproject.api.response.ArrayResponse;
import com.android.msaproject.model.Login;
import com.android.msaproject.service.Retrofit;
import com.android.msaproject.util.Utils;

import java.util.List;

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

        if (login.getUserId().isEmpty()) {
            valid = false;
            mView.onErrorEmptyUserId();
        } else if (login.getPassword().isEmpty()) {
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
            Call<ArrayResponse<LoginData>> call = service.login(login.getUserId(), login.getPassword());
            call.enqueue(new Callback<ArrayResponse<LoginData>>() {
                @Override
                public void onResponse(Call<ArrayResponse<LoginData>> call, Response<ArrayResponse<LoginData>> response) {
                    String status = response.body().getStatus();
                    if(status.equals("200")){
                        List<LoginData> data = response.body().getData();
                        mView.onSuccess(data);
                    }else{
                        mView.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<ArrayResponse<LoginData>> call, Throwable t) {
                    mView.onFailed();
                }
            });
        }else{
            mView.onOffline();
        }
    }

}