package com.android.msaproject.ui.login;

import android.app.Activity;
import android.widget.Toast;

import com.android.msaproject.api.UserAPI;
import com.android.msaproject.api.data.LoginData;
import com.android.msaproject.api.response.BaseArrayResponse;
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
        mView.onPreProcess();
        if(Utils.haveNetworkConnection(mActivity)){
            UserAPI service = Retrofit.setup().create(UserAPI.class);
            Call<BaseArrayResponse<LoginData>> call = service.login(model.getUserId(), model.getPassword());
            call.enqueue(new Callback<BaseArrayResponse<LoginData>>() {
                @Override
                public void onResponse(Call<BaseArrayResponse<LoginData>> call, Response<BaseArrayResponse<LoginData>> response) {
                    String status = response.body().getStatus();
                    if(status.equals("200")){
                        List<LoginData> data = response.body().getData();
                        mView.onSuccess(data);
                    }else{
                        mView.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<BaseArrayResponse<LoginData>> call, Throwable t) {
                    mView.onFailed();
                }
            });
        }else{
            mView.onOffline();
        }
    }

}