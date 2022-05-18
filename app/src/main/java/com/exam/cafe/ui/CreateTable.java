package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.db.TableDBHandle;
import com.exam.cafe.dto.Table;

public class CreateTable extends AppCompatActivity {
    TableDBHandle tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);
        Button create = findViewById(R.id.create_table);

        tb = new TableDBHandle(this.getApplicationContext());
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tableNumber = findViewById(R.id.tableNumber);
                TextView tableFloor = findViewById(R.id.tableFloor);

                int tbNum = Integer.parseInt(tableNumber.getText().toString());
                int tbFloor = Integer.parseInt(tableFloor.getText().toString());

                boolean cr = tb.addTable(new Table(1,(int) tbNum,tbFloor, "chưa ngồi"));
                if(cr){
                    Toast.makeText(getApplicationContext(),"Tạo bàn thành công ✌🐧✌",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Thất bại !!, Lỗi khi tạo bàn",Toast.LENGTH_LONG).show();
                }
                }
            });
    };
}