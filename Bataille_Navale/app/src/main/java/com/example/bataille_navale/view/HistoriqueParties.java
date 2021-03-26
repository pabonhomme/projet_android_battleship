package com.example.bataille_navale.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.CustomGridAdapterHistorique;
import com.example.bataille_navale.manager.GameManager;

import java.io.FileNotFoundException;
import java.util.UUID;

public class HistoriqueParties extends AppCompatActivity {

    private final GameManager gmanager = GameManager.getInstance();
    TextView jamais_joue_historique = null;
    CustomGridAdapterHistorique adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique_parties);

        jamais_joue_historique = findViewById(R.id.jamais_joue_historique);
        if (gmanager.getHistorique().size() != 0) {
            jamais_joue_historique.setVisibility(View.GONE);
        }
        RecyclerView historiqueView = findViewById(R.id.historiques_parties);
        historiqueView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomGridAdapterHistorique(gmanager.getHistorique(), new CustomGridAdapterHistorique.GridAdapterCallback() {
            @Override
            public void deletePartie(UUID id) {
                gmanager.suppressionPartieHistorique(id);
                try {
                    gmanager.sauvegarderDonnees(openFileOutput(GameManager.NAME_FILE, MODE_PRIVATE));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (gmanager.getHistorique().size() == 0) {
                    jamais_joue_historique.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }
        });
        historiqueView.setAdapter(adapter);
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
