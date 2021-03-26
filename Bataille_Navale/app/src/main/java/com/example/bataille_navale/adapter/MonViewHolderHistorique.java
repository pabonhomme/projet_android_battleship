package com.example.bataille_navale.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Partie;

public class MonViewHolderHistorique extends RecyclerView.ViewHolder {

    final TextView leTextViewHistorique;
    final Button supprimer_partie;

    public MonViewHolderHistorique(@NonNull View itemView) {
        super(itemView);
        leTextViewHistorique = itemView.findViewById(R.id.cellule_historique);
        supprimer_partie = itemView.findViewById(R.id.supprimer_partie);
    }

    void bindView(Partie partie, CustomGridAdapterHistorique.GridAdapterCallback callback) {
        leTextViewHistorique.setText(partie.getjGagnant().getPseudo() + " a gagné face à " + partie.getjPerdant().getPseudo() + " en détruisant " + partie.getjGagnant().getScore() + " de ses bateaux contre " + partie.getjPerdant().getScore());

        // Set listener
        supprimer_partie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.deletePartie(partie.getId());
            }
        });
    }
}
