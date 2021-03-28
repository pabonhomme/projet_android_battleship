package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.manager.GameManager;
import com.example.bataille_navale.manager.MusicManager;

public class Menu extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private final MusicManager gMusique = MusicManager.getInstance();

    Button button_menu_jouer = null;
    Button button_menu_historique = null;
    Button button_menu_retour = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        gMusique.putMusic(getApplicationContext(), R.raw.musique_menu);


        button_menu_jouer = findViewById(R.id.menu_jouer);
        button_menu_jouer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gmanager.lancerPartie();
                Intent intent = new Intent(Menu.this, FormJoueur.class);
                startActivity(intent);
                finish();
            }
        });

        button_menu_historique = findViewById(R.id.menu_historique);
        button_menu_historique.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, HistoriqueParties.class);
                startActivity(intent);
            }
        });

        button_menu_retour = findViewById(R.id.menu_retour);
        button_menu_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Accueil.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        gMusique.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gMusique.putMusic(getApplicationContext(), R.raw.musique_menu);
        super.onResume();
    }
}
