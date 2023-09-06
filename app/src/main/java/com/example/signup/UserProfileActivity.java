package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvNickName, tvAccount, tvAge, tvGender, tvCity, tvHome, tvSchool, tvSign, tvBirthdayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
    }

    private void initData() {
        getDataFromSpf();
    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String nickName = spfRecord.getString("nick_name", "");
        String account = spfRecord.getString("account", "");
        String age = spfRecord.getString("age", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String bithDayTime = spfRecord.getString("bith_day_time", "");
        String sign = spfRecord.getString("sign", "");

        tvNickName.setText(nickName);
        tvAccount.setText(account);
        tvAge.setText(age);
        tvGender.setText(gender);
        tvCity.setText(city);
        tvSchool.setText(school);
        tvBirthdayTime.setText(bithDayTime);
        tvSign.setText(sign);
    }

    private void initView() {
        tvNickName = findViewById(R.id.tv_nick_name);
        tvAccount = findViewById(R.id.tv_account_text);
        tvAge = findViewById(R.id.tv_age);
        tvGender = findViewById(R.id.tv_gender);
        tvCity = findViewById(R.id.tv_city);
        tvHome = findViewById(R.id.tv_home_text);
        tvSchool = findViewById(R.id.tv_school_text);
        tvSign = findViewById(R.id.tv_sign_text);
        tvBirthdayTime = findViewById(R.id.tv_birth_time_text);
    }


    public void toEdit(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spfRecord.edit();
        edit.putBoolean("isLogin", false);
        edit.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}