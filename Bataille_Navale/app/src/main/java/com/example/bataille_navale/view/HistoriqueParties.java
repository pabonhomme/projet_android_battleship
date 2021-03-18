package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.CustomGridAdapterHistorique;
import com.example.bataille_navale.model.GameManager;

import java.io.FileNotFoundException;
import java.util.UUID;

public class HistoriqueParties extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();
    Button bouton_retour_historique = null;
    CustomGridAdapterHistorique adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique_parties);

        bouton_retour_historique = findViewById(R.id.bouton_retour_historique);

        RecyclerView historiqueView = findViewById(R.id.historiques_parties);
        gmanager.lancerPartie();
        historiqueView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomGridAdapterHistorique(gmanager.getHistorique(), new CustomGridAdapterHistorique.GridAdapterCallback() {
            @Override
            public void deletePartie(UUID id) {
                gmanager.suppressionPartiehistorique(id);
                adapter.notifyDataSetChanged();
            }
        });
        historiqueView.setAdapter( adapter);

        bouton_retour_historique.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            gmanager.sauvegarderDonnees(openFileOutput(GameManager.NAME_FILE, MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
