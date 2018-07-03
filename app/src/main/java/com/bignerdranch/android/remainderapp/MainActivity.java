package com.bignerdranch.android.remainderapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    DBHelper mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reminder");

        Map<String, String> map = new HashMap<>();
        map.put("Date", "3rd,Jully,2018");
        map.put("Time", "6:00 PM");


        DatabaseReference mDataRef = databaseReference.push();
        mDataRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Upload done", Toast.LENGTH_LONG).show();

            }
        });

        mDb = new DBHelper(this);
        boolean isInserted = mDb.insertData("12/05/2018","Test");
        //test
    }
}
