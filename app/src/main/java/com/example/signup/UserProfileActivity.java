package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
    }

    // 编辑完信息保存后跳转到页面时，不会执行上面的onCreate函数，就不会进行信息的更新，所以在onResume函数中进行数据的更新
    @Override
    protected void onResume() {
        super.onResume();
        initData();  // 在编辑页面对信息进行修改，退出页面回到当前页面时不会执行Oncreate函数，执行onResume函数，进行数据的更新
    }

    private void initData() {
        getDataFromSpf();
    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String nickName = spfRecord.getString("nick_name", "");
        String account = spfRecord.getString("account", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String bithDayTime = spfRecord.getString("birth_day_time", "");
        String sign = spfRecord.getString("sign", "");

        String age = getAgeBirthDay(bithDayTime);

        tvNickName.setText(nickName);
        tvAccount.setText(account);
        tvAge.setText(age);
        tvGender.setText(gender);
        tvCity.setText(city);
        tvSchool.setText(school);
        tvBirthdayTime.setText(bithDayTime);
        tvSign.setText(sign);
    }

    private String getAgeBirthDay(String birthDayTime) {
        if (TextUtils.isEmpty(birthDayTime)) return "";

        try {
            int index = birthDayTime.indexOf("年");
            String year = birthDayTime.substring(0, index);
            Integer age = Integer.parseInt(year);
            return age.toString(2023 - age);
        } catch (Exception e) {
            e.printStackTrace();  // 打印出错信息
        }

        return "";

//       if (TextUtils.isEmpty(birthDayTime)) return "";
//       int index = birthDayTime.indexOf("年");
//       String result = birthDayTime.substring(0, index);
//       Integer age = Integer.parseInt(result);
//       return age.toString(2023 - age);
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