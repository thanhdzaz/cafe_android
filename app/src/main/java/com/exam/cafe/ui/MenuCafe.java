package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.adapter.MenuListAdapter;
import com.exam.cafe.db.Database;
import com.exam.cafe.dialog.CustomDialog;
import com.exam.cafe.dto.Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MenuCafe extends AppCompatActivity {
    Database db;
    public MenuListAdapter n;
    public ListView listView;
    private Button search;
    private EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cafe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db= new Database(this);
        db.executeSQL("CREATE TABLE IF NOT EXISTS MenuList (id INTEGER PRIMARY KEY, name TEXT, price INTEGER)");
        FloatingActionButton add = findViewById(R.id.fab);
        t = findViewById(R.id.search_input);
        search = findViewById(R.id.search_menu);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.seach(t.getText().toString());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MenuCafe.this, AddMenu.class));;
            }
        });
        setTitle("Thực đơn");



        List<Menu> menus = getListData();
        listView = (ListView) findViewById(R.id.list_menu);
        n = new MenuListAdapter(this, menus);
        listView.setAdapter(n);
        n.setSelf(n);




//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(MenuCafe.this, AddMenu.class));
//
//            }
//        });
    }

    private  List<Menu> getListData() {
        List<Menu> list = new ArrayList<Menu>();

        String sql = "select * from MenuList";


        Cursor cs = db.retrieveData(sql);

        while (cs.moveToNext()) {
            list.add(new Menu(cs.getInt(0),cs.getString(1),cs.getInt(2)));
        }

        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        n.refreshDB();
    }
}