package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.cafe.db.Database;
import com.exam.cafe.dto.Menu;

import com.exam.cafe.R;

public class EditMenu extends AppCompatActivity {
    private Menu menu;
    private EditText name,price;
    private Button btn;
    private Database db;
    private int id,pricetxt;
    private  String nametxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nametxt = intent.getStringExtra("name");
        pricetxt = intent.getIntExtra("price",0);
        setTitle("Cập nhật thực đơn: " + nametxt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new Database(this);

        name = findViewById(R.id.ed_menu_name);
        price = findViewById(R.id.ed_menu_price);

        name.setText(nametxt);
        price.setText(""+pricetxt);

        btn = findViewById(R.id.edit_menu_hehe);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = String.format("UPDATE MenuList SET name='%s', price=%s WHERE id=%s",name.getText().toString(),price.getText().toString(),id);
                if(db.executeSQL(sql)){
                    Toast.makeText(EditMenu.this, "Cập nhật thành công 🎉🎉", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(EditMenu.this, "Cập nhật thất bại 🙌🙌", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}