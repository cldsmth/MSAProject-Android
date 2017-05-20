package com.android.msaproject.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.msaproject.R;
import com.android.msaproject.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginActivity _this = this;
    private LoginModel model;
    private LoginPresenter presenter;

    @Bind(R.id.input_text_user)
    TextInputLayout errorUser;
    @Bind(R.id.input_user)
    EditText inputUser;
    @Bind(R.id.input_text_password)
    TextInputLayout errorPassword;
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(_this);
        model = new LoginModel();
        presenter = new LoginPresenterImp(this, _this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setUserId(Utils.textInput(inputUser));
                model.setPassword(Utils.textInput(inputPassword));
                presenter.validate(model);
            }
        });
    }

    @Override
    public void onValidate(boolean valid) {
        if (!valid) {
            return;
        } else {
            presenter.submit(model);
        }
    }

    @Override
    public void onSuccess() {
        Utils.displayToast(_this, "Success", Toast.LENGTH_SHORT);
    }

    @Override
    public void onFailed() {
        Utils.displayToast(_this, "Failed", Toast.LENGTH_SHORT);
    }

    @Override
    public void onErrorEmptyUserId() {
        inputUser.requestFocus();
        errorUser.setError("User Id tidak boleh kosong");
    }

    @Override
    public void onErrorEmptyPassword() {
        inputPassword.requestFocus();
        errorPassword.setError("Password tidak boleh kosong");
    }

}