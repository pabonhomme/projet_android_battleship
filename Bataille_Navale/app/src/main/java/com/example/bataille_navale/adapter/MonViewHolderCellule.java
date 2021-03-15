package com.example.bataille_navale.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;

public class MonViewHolderCellule extends RecyclerView.ViewHolder {

    private final TextView leTextView;

    public MonViewHolderCellule(@NonNull View itemView) {
        super(itemView);
        leTextView = itemView.findViewById(R.id.cellule);
    }

    public TextView getLeTextView() {
        return leTextView;
    }
}
