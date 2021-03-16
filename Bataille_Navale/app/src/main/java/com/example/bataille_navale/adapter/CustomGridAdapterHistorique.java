package com.example.bataille_navale.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomGridAdapterHistorique extends RecyclerView.Adapter<MonViewHolderHistorique> {

    private final List<Partie> historique;
    private Partie partie;

    public CustomGridAdapterHistorique(List<Partie> historique){
        this.historique = historique;
    }

    /**
     * Parse la celulle et crée le holder (contenant) qui va contenir la celulle
     * @param parent parent qui va contenir la cellule
     * @param viewType type de vue
     * @return ViewHolder
     */
    @NonNull
    @Override
    public MonViewHolderHistorique onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout leLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.partie, parent, false );
        return new MonViewHolderHistorique(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolderHistorique holder, int position) {
        partie = historique.get(position);
        holder.getLeTextViewHistorique().setText( partie.getjGagnant().getPseudo() + " a gagné face à " +  partie.getjPerdant().getPseudo() + " en détruisant " + partie.getjGagnant().getScore() +" de ses bateaux contre " + partie.getjPerdant().getScore());
    // pour le moment on concatène comme ça après on mettra en ressource string
    }

    @Override
    public int getItemCount() {
        return historique.size();
    }
}
