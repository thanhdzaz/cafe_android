package com.exam.cafe.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.db.Database;

public class AddMenu extends AppCompatActivity {
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Thêm thực đơn");
        db= new Database(this);
        Button add = findViewById(R.id.add_menu_hehe);
        EditText name = findViewById(R.id.ad_menu_name);
        EditText price = findViewById(R.id.ad_menu_price);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddMenu.this,".",Toast.LENGTH_SHORT).show();

                try {


                String sql = String.format("INSERT INTO MenuList (id,name,price) values (null, '%s', %s)",name.getText(),price.getText());
                if(db.executeSQL(sql)){
                    Toast.makeText(AddMenu.this,"Thêm thực đơn thành công 🎉🎉",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddMenu.this,"Chà chà lỗi rồi Fen",Toast.LENGTH_SHORT).show();
                }
                } catch (Exception e) {
                e.printStackTrace();

                    Log.e(TAG, e.getLocalizedMessage() );
            }
            }
        });
    }
}