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
import com.example.bataille_navale.model.Bateau;
import com.example.bataille_navale.model.Cellule;
import com.example.bataille_navale.model.GameManager;
import com.example.bataille_navale.model.Orientation;

public class Placement_Bateau extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_placement = null;
    private TextView bat_restant_placement = null;
    private TextView taille_bat_placement = null;
    private RadioGroup orientationGroup = null;
    private Button suivant_placement = null;
    Orientation orientation = null;

    private int bateauPlace = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_bateau);

        nomJoueur_placement = findViewById(R.id.nomJoueur_placement);
        bat_restant_placement = findViewById(R.id.bat_restant_placement);
        taille_bat_placement = findViewById(R.id.taille_bat_placement);
        orientationGroup = findViewById(R.id.orientation_placement);
        suivant_placement = findViewById(R.id.suivant_placement);

        nomJoueur_placement.setText(getResources().getString(R.string.pseudo_placement, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
        bat_restant_placement.setText(getResources().getString(R.string.bat_restant_placement, gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX - bateauPlace)); // set texte nb bateaux restants
        taille_bat_placement.setText(getResources().getString(R.string.taille_bat_placement, gmanager.getJoueurEnCours().getPlateau().TAILLE_NAVIRES[bateauPlace])); // set texte taille bateau à placer

        final GridView gridView = findViewById(R.id.gridView_placement); // on récupère la grid
        final GridAdapterPlacement gridAdapterPlacement = new GridAdapterPlacement(this, gmanager.getJoueurEnCours().getPlateau().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapterPlacement);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                if (bateauPlace < gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX) {
                    if (orientationGroup.getCheckedRadioButtonId() == R.id.horizontal) {
                        orientation = Orientation.HORIZONTAL;
                    } else orientation = Orientation.VERTICAL;

                    Cellule cell = (Cellule) gridAdapterPlacement.getItem(position);
                    if (gmanager.getJoueurEnCours().getPlateau().positionneBateau(new Bateau(cell.getPositions().first, cell.getPositions().second, orientation, gmanager.getJoueurEnCours().getPlateau().TAILLE_NAVIRES[bateauPlace]))) { // on teste si on peut positionner le bateau
                        bateauPlace++;
                        bat_restant_placement.setText(getResources().getString(R.string.bat_restant_placement, gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX - bateauPlace)); // set texte nb bateaux restants
                        if (bateauPlace == gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX) { // on vérifie si on ne dépasse pas l'index maximal du tableau
                            taille_bat_placement.setVisibility(View.INVISIBLE);
                        } else {
                            taille_bat_placement.setText(getResources().getString(R.string.taille_bat_placement, gmanager.getJoueurEnCours().getPlateau().TAILLE_NAVIRES[bateauPlace])); // set texte taille bateau à placer
                        }
                    } else {
                        Toast.makeText(Placement_Bateau.this, "Le bateau ne peut pas être positionné à cette position", Toast.LENGTH_SHORT).show();
                    }
                }
                // This tells the GridView to redraw itself
                // in turn calling your GridAdapterPlacement's getView method again for each cell
                gridAdapterPlacement.notifyDataSetChanged();
            }
        });

        suivant_placement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (bateauPlace == gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX) {
                    gmanager.changementJoueur();
                    if (gmanager.getJoueurEnCours() == gmanager.getJ2()) {
                        bateauPlace = 0;
                        bat_restant_placement.setText(getResources().getString(R.string.bat_restant_placement, gmanager.getJoueurEnCours().getPlateau().NB_BATEAUX - bateauPlace)); // set texte nb bateaux restants
                        taille_bat_placement.setText(getResources().getString(R.string.taille_bat_placement, gmanager.getJoueurEnCours().getPlateau().TAILLE_NAVIRES[bateauPlace])); // set texte taille bateau à placer
                        taille_bat_placement.setVisibility(View.VISIBLE);
                        gridAdapterPlacement.refreshData(gmanager.getJoueurEnCours().getPlateau().getGrille());
                    } else {
                        Intent intent = new Intent(Placement_Bateau.this, Plateau_Jeu.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(Placement_Bateau.this, "Vous n'avez pas placé tous les bateaux", Toast.LENGTH_SHORT).show();
                }

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
