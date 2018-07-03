package com.bignerdranch.android.remainderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DBHelper mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = new DBHelper(this);
        boolean isInserted = mDb.insertData("12/05/2018","Test");
        //test
    }
}
