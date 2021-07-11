package com.example.accountapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private UserOperator musterOprator;
    private Button btRegister;
    private ImageView imBack;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etVerificationCode;
    private ImageView imVerificationCode;
    private String realCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        musterOprator = new UserOperator(this);
        init();

//        imVerificationCode.setImageBitmap();
//        realCode =
    }

    private void init(){
        btRegister = findViewById(R.id.bt_register);
        imBack = findViewById(R.id.iv_register_back);
        etUserName = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etVerificationCode = findViewById(R.id.et_register_verification_code);
        imVerificationCode = findViewById(R.id.iv_register_verification_code);

        imBack.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        imVerificationCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_register_back:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.iv_register_verification_code:
                break;
            case R.id.bt_register:
                String userName = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();

                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(verificationCode)){
                    Utils.toast(this, "请将填写完整注册信息");
                } else {
                    if(!password.equals(confirmPassword)){
                        Utils.toast(this, "请确认两次密码相同");
                    }
                    else if(verificationCode.equals(realCode)){
                        User bean = musterOprator.isExit(userName);
                        if(bean != null){
                            Utils.toast(this, "该用户已注册");
                        }
                        else{
                            musterOprator.addUser(new User(userName, password));
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                            Utils.toast(this, "用户注册成功");
                        }
                    }
                    else{
                        Utils.toast(this, "验证码错误");
                    }
                }
                break;
        }
    }
}
