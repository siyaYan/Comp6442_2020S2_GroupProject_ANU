package com.example.comp2100_6442_s2_2020_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHistoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userData";
    private static final String TABLE_NAME = "userHistory";

    UserHistoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(id TEXT , history TEXT )";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addToDB(String userID, String course) {
        SQLiteDatabase writedb = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("id", userID);
        cv.put("history", course);
        writedb.insert(TABLE_NAME, null, cv);
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

    public void updateHistory(String userID, String course) {
        SQLiteDatabase writedb = this.getWritableDatabase();
        String history = getHistory(userID);

        //no duplicates in history
        if(!history.contains(course)){
            ContentValues cv = new ContentValues();
            cv.put("id", userID);
            cv.put("history",  history + " " + course);
            writedb.update(TABLE_NAME, cv, "id = " + userID, null);
        }
    }

    public String getHistory(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String historyList = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            if (id.equals(userID)) {
                historyList = cursor.getString(cursor.getColumnIndex("history"));
                break;
            }
            cursor.moveToNext();
        }
        return historyList;
    }
}
