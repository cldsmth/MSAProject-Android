package com.android.msaproject.api;

import com.android.msaproject.api.data.LoginData;
import com.android.msaproject.api.response.ObjectResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {
    /**
     * Login
     */
    @FormUrlEncoded
    @POST("api_user_account.php?action=login")
    Call<ObjectResponse<LoginData>> login(
            @Field("user_id") String user_id,
            @Field("password") String password
    );
}