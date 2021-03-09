package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.GridAdapterJeu;
import com.example.bataille_navale.adapter.GridAdapterPlacement;
import com.example.bataille_navale.model.GameManager;

public class Affichage_Gagnant extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_gagnant = null;
    private TextView pseudo_plateau_adverse = null;
    private Button menu_gagnant = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_gagnant);

        nomJoueur_gagnant = findViewById(R.id.nomJoueur_gagnant);
        pseudo_plateau_adverse = findViewById(R.id.pseudo_plateau_adverse);
        menu_gagnant = findViewById(R.id.menu_gagnant);

        nomJoueur_gagnant.setText(getResources().getString(R.string.pseudo_gagnant, gmanager.getJoueurEnCours().getPseudo())); // set texte nb bateaux restants
        if(gmanager.getJoueurEnCours() == gmanager.getJ1()){
            pseudo_plateau_adverse.setText(getResources().getString(R.string.pseudo_plateau_adverse, gmanager.getJ2().getPseudo()));
        }
        else {
            pseudo_plateau_adverse.setText(getResources().getString(R.string.pseudo_plateau_adverse, gmanager.getJ1().getPseudo()));
        }


        final GridView gridView = findViewById(R.id.gridView_jeu); // on récupère la grid
        final GridAdapterPlacement gridAdapter = new GridAdapterPlacement(this, gmanager.getJoueurEnCours().getPlateau().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapter);

        menu_gagnant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Affichage_Gagnant.this, Menu.class);
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
