package com.exam.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exam.cafe.R;
import com.exam.cafe.db.SessionDBHandle;
import com.exam.cafe.dto.SessionUser;
import com.exam.cafe.ui.CreateTable;
import com.exam.cafe.ui.Login;
import com.exam.cafe.ui.Register;
import com.exam.cafe.ui.TableCafeManager;

public class MainActivity extends AppCompatActivity {
    SessionDBHandle session;
    TextView welcome;
    Button login,register,manager,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionDBHandle(this.getApplicationContext());
        session.init();
        welcome = findViewById(R.id.welcome);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        manager = findViewById(R.id.menu);
        logout = findViewById(R.id.logout);

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TableCafeManager.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logout();
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SessionUser ss = session.isLoggedIn();
        if(ss!=null){
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            manager.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            Log.e(MainActivity.class.getName(), String.valueOf(ss.getUserName()));
            welcome.setText("Chào mừng " + ss.getUserName() + ", ngày mới tốt lành!!");
        }else{
            Log.e(MainActivity.class.getName(), String.valueOf("noUser"));
            login.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            manager.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        }


    }
}