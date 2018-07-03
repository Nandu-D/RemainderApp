package com.bignerdranch.android.remainderapp;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView.Adapter mRemainderAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolbar;

    private EditRemainderDialog mEditRemainderDialog = null;
    private DatabaseReference databaseReference;

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


        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);

        mRemainderAdapter = new RemainderAdapter(getSupportFragmentManager());
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRemainderAdapter);

        mToolbar.setTitle("Remainder App");

        mEditRemainderDialog = new EditRemainderDialog(new DialogClickActions() {
            @Override
            public void cancelButtonClickAction() {
                mEditRemainderDialog.dismiss();
            }

            @Override
            public void editButtonClickAction() {

            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditRemainderDialog.show(getSupportFragmentManager(), "Dialog");
                mEditRemainderDialog.changeTitleOfDialog("Add Remainder");
                mEditRemainderDialog.changeTextOfButton("Add");

            }
        });
    }
}
