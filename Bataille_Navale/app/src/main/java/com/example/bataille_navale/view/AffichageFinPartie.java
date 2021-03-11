package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.GridAdapterPlacement;
import com.example.bataille_navale.model.GameManager;

public class AffichageFinPartie extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_gagnant_finPartie = null;
    private TextView pseudo_plateau_adverse_finPartie = null;
    private Button rejouer_gagnant_finPartie = null;
    private Button menu_gagnant_finPartie = null;

    private ImageView imageGagnant_finPartie = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_fin_partie);

        nomJoueur_gagnant_finPartie = findViewById(R.id.nomJoueur_gagnant_finPartie);
        pseudo_plateau_adverse_finPartie = findViewById(R.id.pseudo_plateau_adverse_finPartie);
        rejouer_gagnant_finPartie = findViewById(R.id.rejouer_gagnant_finPartie);
        menu_gagnant_finPartie = findViewById(R.id.menu_gagnant_finPartie);
        imageGagnant_finPartie = findViewById(R.id.imageGagnant_finPartie);

        nomJoueur_gagnant_finPartie.setText(getResources().getString(R.string.pseudo_gagnant, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants

        if (gmanager.getJoueurEnCours().getImageJoueur() != null) {
            imageGagnant_finPartie.setImageBitmap(gmanager.getJoueurEnCours().getImageJoueur());
        }
        pseudo_plateau_adverse_finPartie.setText(getResources().getString(R.string.pseudo_plateau_gagnant, gmanager.getJoueurEnCours().getPseudo())); // set pseudo pour plateau gagnant


        final GridView gridView_finPartie = findViewById(R.id.gridView_finPartie); // on récupère la grid
        final GridAdapterPlacement gridAdapter = new GridAdapterPlacement(this, gmanager.getJoueurEnCours().getPlateau().getGrille()); // on set l'adapter
        gridView_finPartie.setAdapter(gridAdapter);

        rejouer_gagnant_finPartie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gmanager.rejouer();
                Intent intent = new Intent(AffichageFinPartie.this, PlacementBateau.class);
                startActivity(intent);
                finish();
            }
        });

        menu_gagnant_finPartie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AffichageFinPartie.this, Menu.class);
                startActivity(intent);
                finish();
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
