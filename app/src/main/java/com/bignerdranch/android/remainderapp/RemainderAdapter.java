package com.bignerdranch.android.remainderapp;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RemainderAdapter extends RecyclerView.Adapter<RemainderAdapter.ViewHolder> {
    private EditRemainderDialog editRemainderDialog = null;
    private FragmentManager mFragmentManager = null;

    public RemainderAdapter(FragmentManager supportFragmentManager) {
        mFragmentManager = supportFragmentManager;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRemainderDialog = new EditRemainderDialog(new DialogClickActions() {
                    @Override
                    public void cancelButtonClickAction() {
                        editRemainderDialog.dismiss();
                    }

                    @Override
                    public void editButtonClickAction() {

                    }
                });

                editRemainderDialog.changeTextOfButton("Edit");
                editRemainderDialog.changeTitleOfDialog("Edit Remainder");
                editRemainderDialog.show(mFragmentManager, "Dialog");
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView message, date;
        private ConstraintLayout fullLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            fullLayout = itemView.findViewById(R.id.full_layout);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
        }
    }
}
