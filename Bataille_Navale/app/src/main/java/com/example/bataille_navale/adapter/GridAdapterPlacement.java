package com.example.bataille_navale.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Cellule;

import java.util.ArrayList;

public class GridAdapterPlacement extends BaseAdapter {

    private ArrayList<Cellule> listeCell;
    private LayoutInflater layoutInflater;
    private Context context;
    AlphaAnimation animation = null;

    public GridAdapterPlacement(Context aContext, ArrayList<Cellule> listeCell) {
        this.context = aContext;
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
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MonViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cellule, parent, false);
            holder = new MonViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MonViewHolder) convertView.getTag();
        }

        Cellule cell = listeCell.get(position);
        Drawable background;

        if (cell.faitPartieBateau()) {
            background = parent.getResources().getDrawable(R.drawable.placement_bateau);
            holder.getLeTextView().setBackground(background);
        }


        return convertView;
    }

}