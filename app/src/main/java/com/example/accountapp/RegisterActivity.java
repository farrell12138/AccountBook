package com.example.accountapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
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
    private Switch swUserAgreement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        musterOprator = new UserOperator(this);
        init();
        imVerificationCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode();
    }

    private void init(){
        btRegister = findViewById(R.id.bt_register);
        imBack = findViewById(R.id.iv_register_back);
        etUserName = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etVerificationCode = findViewById(R.id.et_register_verification_code);
        imVerificationCode = findViewById(R.id.iv_register_verification_code);
        swUserAgreement = findViewById(R.id.sw_register_user_agreement);

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
                imVerificationCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
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
                    else if(Utils.equals(verificationCode, realCode)){
                        User bean = musterOprator.isExit(userName);
                        if(!swUserAgreement.isChecked()){
                            Utils.toast(this, "请先阅读并同意《用户协议》");
                        }
                        else if(bean != null){
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
            default:
                break;
        }
    }
}
