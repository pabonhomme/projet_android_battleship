package com.example.bataille_navale.adapter;

import android.content.Context;
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
    final Context activity;

    /**
     * constructeur
     *
     * @param itemView View
     * @param context Context
     */
    public MonViewHolderHistorique(@NonNull View itemView, Context context) {
        super(itemView);
        leTextViewHistorique = itemView.findViewById(R.id.cellule_historique);
        supprimer_partie = itemView.findViewById(R.id.supprimer_partie);
        activity = context;
    }

    /**
     * Permet de mettre du texte dans le textView d'une cellule de l'historique
     *
     * @param partie   Partie
     * @param callback CustomGridAdapterHistorique.GridAdapterCallback
     */
    void bindView(Partie partie, CustomGridAdapterHistorique.GridAdapterCallback callback) {
        leTextViewHistorique.setText(activity.getResources().getString(R.string.cellule_historique, partie.getjGagnant().getPseudo(), partie.getjPerdant().getPseudo(), partie.getjGagnant().getScore(), partie.getjPerdant().getScore()));
        // Set listener
        supprimer_partie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.deletePartie(partie.getId());
            }
        });
    }
}
