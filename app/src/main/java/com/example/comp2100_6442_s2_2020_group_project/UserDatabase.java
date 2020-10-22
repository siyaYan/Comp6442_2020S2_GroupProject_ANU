package com.example.comp2100_6442_s2_2020_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users";
    private static final String TABLE_NAME = "userDetails";

    UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(id TEXT , username TEXT, password TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String userID =cursor.getString(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            UserBarry user = new UserBarry(userID,username,password);
            userList.add(user.id);

            cursor.moveToNext();
        }

        return userList;
    }

    public boolean userExists(String userID) {

        boolean flag = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            if (id.equals(userID)) {
                flag = true;
                break;
            }
            cursor.moveToNext();
        }

        return flag;
    }

    public void registerUser(String userID,String username, String password) {
        SQLiteDatabase writedb = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("id", userID);
        cv.put("username", username);
        cv.put("password",password);
        writedb.insert(TABLE_NAME, null, cv);
    }

    public UserBarry getUserDetails(String userID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        UserBarry user;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            if (id.equals(userID)) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                user = new UserBarry(userID,username,password);
                return user;
            }
            cursor.moveToNext();
        }
        return null;

    }
}
