package com.exam.cafe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.adapter.MenuListAdapter;
import com.exam.cafe.adapter.TableListAdapter;
import com.exam.cafe.db.Database;
import com.exam.cafe.dto.Table;

import java.util.ArrayList;
import java.util.List;

public class ListTable extends AppCompatActivity {
    public TableListAdapter n;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_table);

        List<Table> table = getListData();
        listView = (ListView) findViewById(R.id.listView);
        n = new TableListAdapter(this, table);
        listView.setAdapter(n);

        n.setSelf(n);
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Table table = (Table) o;
                Toast.makeText(ListTable.this, "Selected :" + " " + table, Toast.LENGTH_LONG).show();
            }
        });
    }
    private  List<Table> getListData() {
        List<Table> list = new ArrayList<Table>();

        String sql = "select * from TableList";

        Database db = new Database(this.getApplicationContext());

        Cursor cs = db.retrieveData(sql);

        while (cs.moveToNext()) {
            list.add(new Table(cs.getInt(0),cs.getInt(1),cs.getInt(2),cs.getString(3)));
        }

        return list;
    }
    @Override
    protected void onResume() {
        super.onResume();
        n.refreshDB();
    }

}