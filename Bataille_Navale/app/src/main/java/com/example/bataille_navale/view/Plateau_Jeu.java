package com.example.bataille_navale.view;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.GridAdapterJeu;
import com.example.bataille_navale.model.Cellule;
import com.example.bataille_navale.model.GameManager;

public class Plateau_Jeu extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_jeu = null;
    private TextView bat_restant_jeu = null;
    private Button suivant_jeu = null;

    AlphaAnimation animation = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plateau_jeu);

        nomJoueur_jeu = findViewById(R.id.nomJoueur_jeu);
        bat_restant_jeu = findViewById(R.id.bat_restant_jeu);
        suivant_jeu = findViewById(R.id.suivant_jeu);

        nomJoueur_jeu.setText(getResources().getString(R.string.nomJoueur_jeu, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, gmanager.getJoueurEnCours().getPlateauAdverse().NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants

        final GridView gridView = findViewById(R.id.gridView_jeu); // on récupère la grid
        final GridAdapterJeu gridAdapter = new GridAdapterJeu(this, gmanager.getJoueurEnCours().getPlateauAdverse().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                Cellule cell = (Cellule) gridAdapter.getItem(position);


                if (!gmanager.getajoue() || gmanager.getaTouche()) {
                    if (!cell.estVisitee()) {
                        cell.visite();
                        gmanager.setaTouche(cell.faitPartieBateau());
                        gmanager.setAjoue(true);
                        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, gmanager.getJoueurEnCours().getPlateauAdverse().NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants
                        gridAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Plateau_Jeu.this, "La case en " + cell.getPositions().first + "," + cell.getPositions().second + " est déjà touchée",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Plateau_Jeu.this, "Vous avez déjà joué, cliquez sur suivant",
                            Toast.LENGTH_SHORT).show();
                }

                if (gmanager.isPartieFinie()) {
                    suivant_jeu.setVisibility(View.GONE);
                    Toast.makeText(Plateau_Jeu.this, "Bien joué ! Partie Terminée !",
                            Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Plateau_Jeu.this, Affichage_Gagnant.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);
                }
            }
        });


        suivant_jeu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (gmanager.getajoue()) {
                    if (!gmanager.getaTouche()) {
                        gmanager.changementTour();
                        nomJoueur_jeu.setText(getResources().getString(R.string.nomJoueur_jeu, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
                        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, gmanager.getJoueurEnCours().getPlateauAdverse().NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants
                        gridAdapter.refreshData(gmanager.getJoueurEnCours().getPlateauAdverse().getGrille());
                    } else {
                        Toast.makeText(Plateau_Jeu.this, "Vous avez touché un bateau, vous pouvez rejouer",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Plateau_Jeu.this, "Vous n'avez pas encore joué, il faut tirer sur une case",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Change le fond de la cellule avec une animation
     *
     * @param leTextView Textview
     * @param background Drawable background de la celulle
     */
    private void touchAnimation(TextView leTextView, Drawable background) {
        animation = new AlphaAnimation(0, 1);
        animation.setDuration(100);
        animation.setInterpolator(new AccelerateInterpolator());
        leTextView.startAnimation(animation);
        leTextView.setBackground(background);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
