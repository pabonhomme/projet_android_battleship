package com.example.bataille_navale.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Partie;

import java.util.List;
import java.util.UUID;


public class CustomGridAdapterHistorique extends RecyclerView.Adapter<MonViewHolderHistorique> {

    /**
     * liste de Parties
     */
    private final List<Partie> historique;

    /**
     * Permet de supprimer une liste
     */
    private final GridAdapterCallback callback;

    /**
     * constructeur
     *
     * @param historique List<Partie>
     * @param callback   GridAdapterCallback
     */
    public CustomGridAdapterHistorique(List<Partie> historique, GridAdapterCallback callback) {
        this.historique = historique;
        this.callback = callback;
    }

    /**
     * Parse la celulle et crée le holder (contenant) qui va contenir la celulle
     *
     * @param parent   parent qui va contenir la cellule
     * @param viewType type de vue
     * @return ViewHolder
     */
    @NonNull
    @Override
    public MonViewHolderHistorique onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GridLayout leLayout = (GridLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.partie, parent, false);
        return new MonViewHolderHistorique(leLayout, parent.getContext());
    }

    /**
     * Permet de binder les informations d'une cellule
     *
     * @param holder   MonViewHolderHistorique
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull MonViewHolderHistorique holder, int position) {
        Partie partie = historique.get(position);
        holder.bindView(partie, callback);
    }

    /**
     * récupère le nombre d'item de la liste
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return historique.size();
    }

    /**
     * interface utile pour la suppression d'une partie
     */
    public interface GridAdapterCallback {

        /**
         * Permet de supprimer une partie
         *
         * @param id UUID
         */
        void deletePartie(UUID id);
    }
}
