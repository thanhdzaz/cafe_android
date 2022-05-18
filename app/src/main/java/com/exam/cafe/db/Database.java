package com.exam.cafe.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context) {
        super(context, "CafeManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean executeSQL(String sql){
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.execSQL(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }

    public Cursor retrieveData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);

    }

}
