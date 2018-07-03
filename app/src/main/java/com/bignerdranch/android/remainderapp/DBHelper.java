package com.bignerdranch.android.remainderapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TODO.db";
    public static final String TABLE_NAME = "remainder_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Date";
    public static final String COL_3 = "Message";
    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,MESSAGE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String date, String message) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, message);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result > 0)
            return true;
        else
            return false;

    }

    public ArrayList<RemainderDataModel> getAllData() {
        ArrayList<RemainderDataModel> list = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from remainder_table", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String date = cursor.getString(1);
            String message = cursor.getString(2);
            RemainderDataModel bean = new RemainderDataModel(date, message);

            list.add(bean);
        }
        return list;
    }

    public void deleteEntry(int row) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + "=" + row, null);
    }


}
