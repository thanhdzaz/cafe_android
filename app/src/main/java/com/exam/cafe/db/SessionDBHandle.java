package com.exam.cafe.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.exam.cafe.dto.SessionUser;
import com.exam.cafe.dto.User;

public class SessionDBHandle extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CafeManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Session";

    private static final String KEY_ID = "id";
    private static final String KEY_ID_U = "idUser";
    private static final String KEY_NAME = "userName";
    private static final String KEY_PASSWORD = "password";

    public SessionDBHandle(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_ID_U, KEY_NAME, KEY_PASSWORD);
        db.execSQL(create_user_table);
    }

    public void init(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_ID_U, KEY_NAME, KEY_PASSWORD);
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

    public boolean CreateSession(String us){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase dbR = this.getReadableDatabase();

        Cursor cursor = dbR.query("Users", null, KEY_NAME + " = ?", new String[] { String.valueOf(us) },null, null, null);
        cursor.moveToFirst();
        User user = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_ID,1);
            values.put(KEY_ID_U,user.getId());
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

    public SessionUser isLoggedIn(){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE_NAME, null,  "id = ?", new String[] { String.valueOf(1) },null, null, null);
            cursor.moveToFirst();

            String a = cursor.getString(2);

            if(cursor!=null & cursor.getCount()>0){
                return new SessionUser(1,cursor.getInt(1),cursor.getString(2),cursor.getString(3));
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public void logout(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(1) });
        db.close();
    }

}
