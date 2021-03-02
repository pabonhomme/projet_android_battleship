package com.example.bataille_navale.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Cellule;

// ON NE SE SERT PAS DE CELUI LA, ON UTILISE CustomGridAdapterDeux
public class CustomGridAdapter extends RecyclerView.Adapter<MonViewHolder> {

    private final Cellule[][] grille;

    public CustomGridAdapter(Cellule[][] grille){
        this.grille = grille;
    }

    /**
     * Parse la celulle et crée le holder (contenant) qui va contenir la celulle
     * @param parent parent qui va contenir la cellule
     * @param viewType type de vue
     * @return ViewHolder
     */
    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout leLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule, parent, false );
        return new MonViewHolder(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {

        holder.getLeTextView().setText(grille[position][position].toString());
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", grille.length + " ");
        return grille.length;
    }
}
