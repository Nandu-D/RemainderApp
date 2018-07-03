package com.bignerdranch.android.remainderapp;

import android.app.Dialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
