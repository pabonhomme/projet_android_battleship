package com.example.bataille_navale.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Cellule;

import java.util.ArrayList;

public class GridAdapterJeu extends BaseAdapter {

    private ArrayList<Cellule> listeCell;
    private final LayoutInflater layoutInflater;

    public GridAdapterJeu(Context aContext, ArrayList<Cellule> listeCell) {
        this.listeCell = listeCell;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listeCell.size();
    }

    @Override
    public Object getItem(int position) {
        return listeCell.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshData(ArrayList<Cellule> data) {
        this.listeCell = data;
        this.notifyDataSetChanged();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public View getView(int position, View convertView, ViewGroup parent) {
        MonViewHolderCellule holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cellule, parent, false);
            holder = new MonViewHolderCellule(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MonViewHolderCellule) convertView.getTag();
        }

        Cellule cell = listeCell.get(position);
        Drawable background;
        if (cell.estVisitee()) {

            if (cell.estTouchee()) {
                if (cell.estCoulee()) {
                    background = parent.getResources().getDrawable(R.drawable.coule);
                } else {
                    background = parent.getResources().getDrawable(R.drawable.touche); // si le bateau est touchée
                }
            } else {
                background = parent.getResources().getDrawable(R.drawable.plouf); // si y'a pas de bateau
            }
        } else {
            background = parent.getResources().getDrawable(R.drawable.eau_bg);
        }
        holder.getLeTextView().setBackground(background);


        return convertView;
    }

}
