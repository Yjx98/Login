package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etAccount, etSchool, etSign;
    private TextView tvBirthDayTime;
    private RadioButton rdBoy, rdGirl;
    private AppCompatSpinner spinnerCity;
    private Button btSave;

    private String[] cities;
    private String selectedCity;
    private String birthDay;
    private String birthDayTime;

    private int selectedCityPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        etAccount = findViewById(R.id.et_account_text);
        etSchool = findViewById(R.id.et_school_text);
        etSign = findViewById(R.id.et_sign_text);
        tvBirthDayTime = findViewById(R.id.tv_birth_time_text);
        rdBoy = findViewById(R.id.rb_boy);
        rdGirl = findViewById(R.id.rb_girl);
        spinnerCity = findViewById(R.id.sp_city);
        btSave = findViewById(R.id.bt_save);
    }

    private void initData() {
        cities = getResources().getStringArray(R.array.cities);

        getDataFromSpf();
    }

    private void getDataFromSpf() {
        // 点击编辑界面显示的上一次编辑完成保存后的数据，数据存储在本地中，用来更控件数据
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String account = spfRecord.getString("account", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String bithDayTime = spfRecord.getString("birth_day_time", "");
        String sign = spfRecord.getString("sign", "");

        etAccount.setText(account);
        etSchool.setText(school);
        tvBirthDayTime.setText(bithDayTime);
        etSign.setText(sign);

        if (TextUtils.equals("男", gender)) rdBoy.setChecked(true);
        if (TextUtils.equals("女", gender)) rdGirl.setChecked(true);

        for (int i = 0; i < cities.length; i ++) {
            if (TextUtils.equals(cities[i], city)) {
                selectedCityPosition = i;
                break;
            }
        }

        spinnerCity.setSelection(selectedCityPosition);
    }

    private void initEvent() {

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCity = cities[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvBirthDayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int realMonth = i1 + 1;
                        birthDay = i + "年" + realMonth + "月" + i2 + "日";
                        Log.d("tag", "-----birthDay----" + birthDay);

                        popTimePick();  // 创建选择时间

                    }
                }, 2000, 10, 23).show();
            }
        });


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String school = etSchool.getText().toString();
                String sign = etSign.getText().toString();
                String gender = "男";
                if (rdBoy.isChecked()) {
                    gender = "男";
                }
                if (rdGirl.isChecked()) {
                    gender = "女";
                }

                SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
                SharedPreferences.Editor edit = spfRecord.edit();
                edit.putString("account", account);
                edit.putString("school", school);
                edit.putString("sign", sign);
                edit.putString("birth_day_time", birthDayTime);
                edit.putString("city", selectedCity);
                edit.putString("gender", gender);

                edit.apply();
                EditProfileActivity.this.finish();
            }
        });
    }

    private void popTimePick() {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                birthDayTime = birthDay + i + "时" + i1 + "分";
                Log.d("tag", "-----birthDayTime----" + birthDayTime);
                tvBirthDayTime.setText(birthDayTime);
            }
        }, 12, 34, true).show();
    }

}