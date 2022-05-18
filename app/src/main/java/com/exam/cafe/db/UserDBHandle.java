package com.exam.cafe.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.exam.cafe.dto.User;

public class UserDBHandle extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "CafeManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Users";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "userName";
    private static final String KEY_PASSWORD = "password";

    public UserDBHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user_table = String.format("CREATE TABLE IF EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_PASSWORD);
        db.execSQL(create_user_table);
    }

    public void init(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_PASSWORD);
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


    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, user.getUserName());
            values.put(KEY_PASSWORD, user.getPassword());

            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String user,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor cursor = db.query(TABLE_NAME, null, KEY_NAME + " = ?", new String[] { user },null, null, null);
            cursor.moveToFirst();
            String pass = cursor.getString(2);

                       if(cursor.getCount() > 0 & pass.compareTo(password) == 0) {
                return  true;
            }else {
                return  false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        User us = null;
        try{
            Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(id) },null, null, null);
            cursor.moveToFirst();
            us = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  us;
    }
}
