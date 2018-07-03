package com.bignerdranch.android.remainderapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class EditRemainderDialog extends DialogFragment {
    private DialogClickActions mDialogClickActions;
    private EditText mEditText;
    private TextView mTitleTextView;
    private Button mCancelButton, mEditButton;

    private String titleText, buttonText;

    @SuppressLint("ValidFragment")
    public EditRemainderDialog(DialogClickActions dialogClickActions) {
        mDialogClickActions = dialogClickActions;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.dialog_edit_remainder, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setGravity(Gravity.CENTER);

        mTitleTextView = view.findViewById(R.id.title);
        mEditText = view.findViewById(R.id.edit_remiander_field);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mEditButton = view.findViewById(R.id.edit_button);

        mTitleTextView.setText(titleText);
        mEditButton.setText(buttonText);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogClickActions.cancelButtonClickAction();
            }
        });

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogClickActions.editButtonClickAction();
            }
        });
    }

    public void changeTitleOfDialog(String title) {
        titleText = title;
    }

    public void changeTextOfButton(String text) {
        buttonText = text;
    }

}

interface DialogClickActions {
    void cancelButtonClickAction();
    void editButtonClickAction();
}
