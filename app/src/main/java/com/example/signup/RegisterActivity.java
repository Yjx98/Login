package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextView etAccount, etPassword, etConfirm;
    private Button btRegister;
    private CheckBox cbAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_confirm);
        btRegister = findViewById(R.id.btn_register);
        cbAgree = findViewById(R.id.cb_agree);



        // TextUtils.empty() 空包括两种情况：一个是 null（空指针），另一个是 "“（空字符串）
        // String name; name.isempty() 空只是针对空字符串情况
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirm.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.equals(password, confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!cbAgree.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "请同意用户协议", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 存储：注册成功后要存储注册的用户名和密码
                SharedPreferences spf = getSharedPreferences("spfRecorid", MODE_PRIVATE);
                SharedPreferences.Editor edit = spf.edit();
                edit.putString("account", name);
                edit.putString("password", password);

                // 数据回传
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("account", name);
                bundle.putString("password", password);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);

                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                // 注册成功后回到上一个登陆界面，因为当前页面是从上一个页面跳转过来的，所以结束掉当前页面就直接回到了上一个页面
                RegisterActivity.this.finish();
            }
        });
    }
}





