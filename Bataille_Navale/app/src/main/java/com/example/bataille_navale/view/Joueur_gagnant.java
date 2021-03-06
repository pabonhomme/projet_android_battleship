package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.GameManager;

public class Joueur_gagnant extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private TextView nomJoueur_gagnant = null;
    private Button menu_gagnant = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_gagnant);

        nomJoueur_gagnant = findViewById(R.id.nomJoueur_gagnant);
        menu_gagnant = findViewById(R.id.menu_gagnant);

        nomJoueur_gagnant.setText(getResources().getString(R.string.pseudo_gagnant, gmanager.getPseudoGagnant())); // set texte nb bateaux restants

        menu_gagnant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Joueur_gagnant.this, Menu.class);
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
