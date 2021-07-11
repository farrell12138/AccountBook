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
    private UserOperator musterOperator;
    private TextView tvRegister;
    private EditText etUserName;
    private EditText etPassword;
    private Button btLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        musterOperator = new UserOperator(this);
        initView();
    }

    public void initView() {
        btLogin = findViewById(R.id.bt_login);
        tvRegister = findViewById(R.id.tv_login_register);
        etUserName = findViewById(R.id.et_login_username);
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
                String userName = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                User bean = musterOperator.isExit(userName);
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                    if (bean != null && bean.getPassword().equals(password)) {
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
