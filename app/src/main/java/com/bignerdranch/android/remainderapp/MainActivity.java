package com.bignerdranch.android.remainderapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private RemainderAdapter mRemainderAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolbar;

    private EditRemainderDialog mEditRemainderDialog = null;
    private DatabaseReference databaseReference;
    private DBHelper mDb;
    ArrayList<RemainderDataModel> list;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        mDb = new DBHelper(this);

        //Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reminder");


        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        list = mDb.getAllData();
        mRemainderAdapter = new RemainderAdapter(getSupportFragmentManager(), list);
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
            public void editButtonClickAction(final String date) {
                progressDialog.show();
                boolean isInserted = mDb.insertData("03/07/18", date);
                if (isInserted) {
                    Map<String, String> map = new HashMap<>();
                    map.put("Date", date);
                    map.put("Time", "6:00 PM");
                    DatabaseReference mDataRef = databaseReference.push();
                    mDataRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this, "Upload done", Toast.LENGTH_LONG).show();
                            mRemainderAdapter.setAdapeter(date);
                            mEditRemainderDialog.dismiss();
                            progressDialog.dismiss();

                        }
                    });
                }

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
