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

import java.io.FileNotFoundException;

public class Accueil extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();

    private final MusicManager gMusique = MusicManager.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_accueil);

        gMusique.putMusic(getApplicationContext(), R.raw.musique_menu);

        Button button = findViewById(R.id.accueil_commencer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    gmanager.chargerDonnees(openFileInput(GameManager.NAME_FILE));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Accueil.this, Menu.class);
                startActivity(intent);
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

    @Override
    protected void onDestroy() {
        gMusique.stopMusiq();
        super.onDestroy();
    }
}
