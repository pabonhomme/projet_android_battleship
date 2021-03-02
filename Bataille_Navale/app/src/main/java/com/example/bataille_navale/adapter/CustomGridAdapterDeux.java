package com.example.bataille_navale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Cellule;

import java.util.ArrayList;

public class CustomGridAdapterDeux extends BaseAdapter {

    private final ArrayList<Cellule> listeCell;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridAdapterDeux(Context aContext, ArrayList<Cellule> listeCell ) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        MonViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cellule, parent, false);
            holder = new MonViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MonViewHolder) convertView.getTag();
        }

//        holder.getLeTextView().setText(listeCell.get(position).toString()); // on met une croix dans les cases mais plus besoin background suffit

        return convertView;
    }

}
