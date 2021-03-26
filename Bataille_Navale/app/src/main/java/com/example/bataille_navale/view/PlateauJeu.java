package com.example.bataille_navale.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import com.example.bataille_navale.manager.GameManager;
import com.example.bataille_navale.model.Plateau;

import java.io.FileNotFoundException;

public class PlateauJeu extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_jeu = null;
    private TextView bat_restant_jeu = null;
    private Button suivant_jeu = null;
    private Button quitter_jeu = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plateau_jeu);

        nomJoueur_jeu = findViewById(R.id.nomJoueur_jeu);
        bat_restant_jeu = findViewById(R.id.bat_restant_jeu);
        suivant_jeu = findViewById(R.id.suivant_jeu);
        quitter_jeu = findViewById(R.id.quitter_jeu);

        nomJoueur_jeu.setText(getResources().getString(R.string.nomJoueur_jeu, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, Plateau.NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants

        final GridView gridView = findViewById(R.id.gridView_jeu); // on récupère la grid
        final GridAdapterJeu gridAdapter = new GridAdapterJeu(this, gmanager.getJoueurEnCours().getPlateauAdverse().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                Cellule cell = (Cellule) gridAdapter.getItem(position);


                if (!gmanager.getaJoue() || gmanager.getaTouche()) {
                    if (!cell.estVisitee()) {
                        cell.visite();
                        gmanager.setaTouche(cell.faitPartieBateau());
                        gmanager.setAjoue(true);
                        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, Plateau.NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants
                        gridAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(PlateauJeu.this, getResources().getString(R.string.case_deja_joue, cell.getPositions().first, cell.getPositions().second),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PlateauJeu.this, R.string.erreur_deja_joue,
                            Toast.LENGTH_SHORT).show();
                }

                if (gmanager.isPartieFinie()) {
                    try {
                        gmanager.sauvegarderDonnees(openFileOutput(GameManager.NAME_FILE, MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    suivant_jeu.setVisibility(View.GONE);
                    quitter_jeu.setVisibility(View.GONE);
                    Toast.makeText(PlateauJeu.this, R.string.bj_partie_terminee,
                            Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(PlateauJeu.this, AffichageFinPartie.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);
                }
            }
        });


        suivant_jeu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (gmanager.getaJoue()) {
                    if (!gmanager.getaTouche()) {
                        gmanager.changementTour();
                        nomJoueur_jeu.setText(getResources().getString(R.string.nomJoueur_jeu, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
                        bat_restant_jeu.setText(getResources().getString(R.string.bat_restant_jeu, Plateau.NB_BATEAUX - gmanager.getJoueurEnCours().getPlateauAdverse().nombreBateauxCoules())); // set texte nb bateaux restants
                        gridAdapter.refreshData(gmanager.getJoueurEnCours().getPlateauAdverse().getGrille());
                    } else {
                        Toast.makeText(PlateauJeu.this, R.string.erreur_bateau_touche_rejouer,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PlateauJeu.this, R.string.erreur_pas_joue,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        quitter_jeu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(PlateauJeu.this)
                        .setTitle(R.string.se_retirer_partie_jeu)
                        .setMessage(R.string.question_abandonner__jeu)
                        .setPositiveButton(R.string.retraite_jeu, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Toast.makeText(PlateauJeu.this, R.string.declare_forfait,
                                        Toast.LENGTH_SHORT).show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(PlateauJeu.this, Menu.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 2000);
                            }
                        })
                        .setNegativeButton(R.string.revenir_se_battre_jeu, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });
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
