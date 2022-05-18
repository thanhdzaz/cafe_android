package com.exam.cafe.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exam.cafe.dto.Table;
import com.exam.cafe.dto.User;


public class TableDBHandle extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CafeManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TableList";

    private static final String KEY_ID = "id";
    private static final String KEY_NUM = "number";
    private static final String KEY_FLOOR = "floor";
    private static final String KEY_STATUS = "status";
    String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NUM, KEY_FLOOR, KEY_STATUS);

    public TableDBHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user_table);
    }

    public void init(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(create_user_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_user_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_user_table);

        onCreate(db);
    }


    public boolean addTable(Table table){
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor a = db.rawQuery("select * from " + TABLE_NAME,null);

        try{
            ContentValues values = new ContentValues();
            values.put(KEY_NUM, table.getNum());
            values.put(KEY_FLOOR, table.getFloor());
            values.put(KEY_STATUS, table.getStatus());

            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
