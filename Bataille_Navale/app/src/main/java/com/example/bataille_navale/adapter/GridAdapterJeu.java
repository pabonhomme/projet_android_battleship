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

    /**
     * Constructeur
     *
     * @param aContext  Context
     * @param listeCell ArrayList<Cellule>
     */
    public GridAdapterJeu(Context aContext, ArrayList<Cellule> listeCell) {
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
        this.notifyDataSetChanged();
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
