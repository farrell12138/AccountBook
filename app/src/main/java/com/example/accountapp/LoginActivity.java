package com.example.accountapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LoginActivity extends Activity implements View.OnClickListener {
    private UserOperator muserOperator;
    private TextView tvRegister;
    private EditText etUserNmae;
    private EditText etPassword;
    private Button btLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        muserOperator = new UserOperator(this);

    }

    public void initView() {
        btLogin = findViewById(R.id.bt_login);
        tvRegister = findViewById(R.id.tv_login_register);
        etUserNmae = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);

        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_login:
                String userName = etUserNmae.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                User bean = new User(userName, password);
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                    User temp_bean = muserOperator.isExit(userName);
                    if (bean.getPassword().equals(temp_bean.getPassword())) {
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Utils.toast(this, "用户名或密码不正确, 请重新输入");
                    }
                } else {
                    Utils.toast(this, "请输入用户名及密码");
                }
                break;
        }
    }
}
