package com.example.signup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etAccount, etPassword;
    private CheckBox cbRemember;
    private String userName = "yjx";
    private String userPassword = "123";

    private ActivityResultLauncher<Intent> loginLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        initData();

        loginLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                String account = data.getStringExtra("account");
                String password = data.getStringExtra("password");
                etAccount.setText(account);
                etPassword.setText(password);
                userName = account;
                userPassword = password;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(LoginActivity.this, "还没有注册账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.equals(account, userName) && TextUtils.equals(password, userPassword)) {
                    Toast.makeText(LoginActivity.this, "恭喜您，登陆成功~", Toast.LENGTH_SHORT).show();

                    if (cbRemember.isChecked()) {
                        // 在登陆的时候进行账号和密码的存储
                        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spf.edit();
                        edit.putString("account", account);
                        edit.putString("password", password);
                        edit.putBoolean("isRemember", true);
                        edit.apply();
                    } else {
                        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spf.edit();
                        edit.putBoolean("isRemember", false);
                    }

                    Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);

                    intent.putExtra("account", account);  // 将账户名传给首页，用于显示当前用户名
                    startActivity(intent);
                    LoginActivity.this.finish();  // 跳转过去另一个页面，销毁当前页面
                }

                else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不正确！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
    }

    private void initData() {
        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
        boolean isRemember = spf.getBoolean("isRemember", false);// 取出键值为isRemember的值，默认为false
        String account = spf.getString("account", "");// 初始为空字符串
        String password = spf.getString("password", "");//初始为空

        userName = account;
        userPassword = password;

        if (isRemember) {
            etAccount.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
    }

    public void toRegister(View view) {  // 当点击还没有账号时调用

        // 登陆页面跳转到注册页面
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        loginLauncher.launch(intent);
    }

}