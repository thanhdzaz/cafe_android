package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.db.SessionDBHandle;
import com.exam.cafe.db.UserDBHandle;
import com.exam.cafe.dto.User;

public class Register extends AppCompatActivity {
    UserDBHandle us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Đăng ký");

        us = new UserDBHandle(this.getApplicationContext());
        us.init();

        Button register = findViewById(R.id.registerSubmit);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.rgUser);
                EditText pass = (EditText) findViewById(R.id.rgPassword);

                String userName = user.getText().toString();
                String password = pass.getText().toString();

                if(userName.matches("")){
                    Toast.makeText(getApplicationContext(),"Nhập tài khoản",Toast.LENGTH_LONG).show();
                }else if(password.matches("")){
                    Toast.makeText(getApplicationContext(),"Nhập mật khẩu",Toast.LENGTH_LONG).show();
                }else{
                   boolean l = us.addUser(new User(1,userName,password));

                   if(l){
                       Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                   }
                }
            }
        });


    }
}