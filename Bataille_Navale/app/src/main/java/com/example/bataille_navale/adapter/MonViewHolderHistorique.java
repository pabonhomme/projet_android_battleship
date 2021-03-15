package com.example.bataille_navale.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;

public class MonViewHolderHistorique extends RecyclerView.ViewHolder {

    private final TextView leTextViewHistorique;

    public MonViewHolderHistorique(@NonNull View itemView) {
        super(itemView);
        leTextViewHistorique = itemView.findViewById(R.id.cellule_historique);
    }

    public TextView getLeTextViewHistorique() {
        return leTextViewHistorique;
    }
}
