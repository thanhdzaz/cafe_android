package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.cafe.MainActivity;
import com.exam.cafe.R;
import com.exam.cafe.db.SessionDBHandle;
import com.exam.cafe.db.UserDBHandle;
import com.exam.cafe.dto.User;

public class Login extends AppCompatActivity {
    UserDBHandle us;
    SessionDBHandle ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");


        us = new UserDBHandle(this.getApplicationContext());
        us.init();

        ss=new SessionDBHandle(this.getApplicationContext());
        ss.init();

        Button login = findViewById(R.id.loginSubmit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.lgUser);
                EditText pass = (EditText) findViewById(R.id.lgPass);

                String userName = user.getText().toString();
                String password = pass.getText().toString();

                if(userName.matches("")){
                    Toast.makeText(getApplicationContext(),"Nhập tài khoản",Toast.LENGTH_LONG).show();
                }else if(password.matches("")){
                    Toast.makeText(getApplicationContext(),"Nhập mật khẩu",Toast.LENGTH_LONG).show();
                }else{
                    boolean l = us.login(userName,password);

                    if(l){
                        ss.CreateSession(userName);
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                    }else {
                        Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}