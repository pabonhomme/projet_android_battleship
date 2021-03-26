package com.example.bataille_navale.adapter;

import android.annotation.SuppressLint;
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

    /**
     * Constructeur
     *
     * @param aContext  Context
     * @param listeCell ArrayList<Cellule>
     */
    public GridAdapterPlacement(Context aContext, ArrayList<Cellule> listeCell) {
        this.context = aContext;
        this.listeCell = listeCell;
        layoutInflater = LayoutInflater.from(aContext);
    }

    /**
     * récupère le nombre de cellule
     *
     * @return int
     */
    @Override
    public int getCount() {
        return listeCell.size();
    }

    /**
     * récupère un item à partie d'une position
     *
     * @param position int
     * @return Object
     */
    @Override
    public Object getItem(int position) {
        return listeCell.get(position);
    }

    /**
     * Récupère l'item d'un id
     *
     * @param position int
     * @return long
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Permet de mettre à jour la liste de cellule quand on change d'adversaire
     *
     * @param data ArrayList<Cellule>
     */
    public void refreshData(ArrayList<Cellule> data) {
        this.listeCell = data;
        notifyDataSetChanged();
    }

    /**
     * Permet de récupérer la vue à afficher
     *
     * @param position    int
     * @param convertView View
     * @param parent      ViewGroup
     * @return View
     */
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

        if (cell.faitPartieBateau()) {
            background = parent.getResources().getDrawable(R.drawable.placement_bateau);
        } else {
            background = parent.getResources().getDrawable(R.drawable.eau_bg);
        }
        holder.getLeTextView().setBackground(background);


        return convertView;
    }

}
