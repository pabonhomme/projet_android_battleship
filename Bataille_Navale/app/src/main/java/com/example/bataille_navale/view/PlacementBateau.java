package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.GridAdapterPlacement;
import com.example.bataille_navale.manager.MusicManager;
import com.example.bataille_navale.model.Bateau;
import com.example.bataille_navale.model.Cellule;
import com.example.bataille_navale.manager.GameManager;
import com.example.bataille_navale.model.Orientation;
import com.example.bataille_navale.model.Plateau;

public class PlacementBateau extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private final MusicManager gMusique = MusicManager.getInstance();

    private TextView bat_restant_placement = null;
    private TextView taille_bat_placement = null;
    private RadioGroup orientationGroup = null;
    private Orientation orientation = null;

    private int bateauPlace = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_bateau);

        gMusique.putMusic(getApplicationContext(), R.raw.musique_jeu);

        TextView nomJoueur_placement = findViewById(R.id.nomJoueur_placement);
        bat_restant_placement = findViewById(R.id.bat_restant_placement);
        taille_bat_placement = findViewById(R.id.taille_bat_placement);
        orientationGroup = findViewById(R.id.orientation_placement);
        Button suivant_placement = findViewById(R.id.suivant_placement);
        Button reset_bateaux_placement = findViewById(R.id.reset_bateaux_placement);


        nomJoueur_placement.setText(getResources().getString(R.string.pseudo_placement, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
        setInfosBateaux();

        final GridView gridView = findViewById(R.id.gridView_placement); // on récupère la grid
        final GridAdapterPlacement gridAdapterPlacement = new GridAdapterPlacement(this, gmanager.getJoueurEnCours().getPlateau().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapterPlacement);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                if (bateauPlace < Plateau.NB_BATEAUX) {
                    if (orientationGroup.getCheckedRadioButtonId() == R.id.horizontal) {
                        orientation = Orientation.HORIZONTAL;
                    } else orientation = Orientation.VERTICAL;

                    Cellule cell = (Cellule) gridAdapterPlacement.getItem(position);
                    if (gmanager.getJoueurEnCours().getPlateau().positionneBateau(new Bateau(cell.getPositions().first, cell.getPositions().second, orientation, Plateau.TAILLE_NAVIRES[bateauPlace]))) { // on teste si on peut positionner le bateau
                        bateauPlace++;
                        bat_restant_placement.setText(getResources().getString(R.string.bat_restant_placement, Plateau.NB_BATEAUX - bateauPlace)); // set texte nb bateaux restants
                        if (bateauPlace == Plateau.NB_BATEAUX) { // on vérifie si on ne dépasse pas l'index maximal du tableau
                            taille_bat_placement.setText(R.string.cliquez_sur_suivent_placement);
                        } else {
                            taille_bat_placement.setText(getResources().getString(R.string.taille_bat_placement, Plateau.TAILLE_NAVIRES[bateauPlace])); // set texte taille bateau à placer
                        }
                    } else {
                        Toast.makeText(PlacementBateau.this, R.string.erreur_placement_bateau, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PlacementBateau.this, R.string.bateaux_deja_places, Toast.LENGTH_SHORT).show();
                }
                // This tells the GridView to redraw itself
                // in turn calling your GridAdapterPlacement's getView method again for each cell
                gridAdapterPlacement.notifyDataSetChanged();
            }
        });

        reset_bateaux_placement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (bateauPlace != 0) {
                    gmanager.getJoueurEnCours().getPlateau().resetBateaux();
                    gridAdapterPlacement.notifyDataSetChanged();
                    bateauPlace = 0;
                    setInfosBateaux();
                }
            }
        });

        suivant_placement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (bateauPlace == Plateau.NB_BATEAUX) {
                    gmanager.changementJoueur();
                    if (gmanager.getJoueurEnCours() == gmanager.getJ2()) {
                        bateauPlace = 0;
                        nomJoueur_placement.setText(getResources().getString(R.string.pseudo_placement, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
                        setInfosBateaux();
                        taille_bat_placement.setVisibility(View.VISIBLE);
                        gridAdapterPlacement.refreshData(gmanager.getJoueurEnCours().getPlateau().getGrille());
                    } else {
                        Intent intent = new Intent(PlacementBateau.this, PlateauJeu.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(PlacementBateau.this, R.string.erreur_bateaux_non_places, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Permet de mettre à jour les textView d'informations des bateaux
     */
    private void setInfosBateaux(){
        bat_restant_placement.setText(getResources().getString(R.string.bat_restant_placement, Plateau.NB_BATEAUX - bateauPlace)); // set texte nb bateaux restants
        taille_bat_placement.setText(getResources().getString(R.string.taille_bat_placement, Plateau.TAILLE_NAVIRES[bateauPlace])); // set texte taille bateau à placer
    }

    @Override
    protected void onPause() {
        gMusique.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gMusique.putMusic(getApplicationContext(), R.raw.musique_jeu);
        super.onResume();
    }
}
