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

public class HistoriqueParties extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();
    Button bouton_retour_historique = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique_parties);

        bouton_retour_historique = findViewById(R.id.bouton_retour_historique);

        RecyclerView historiqueView = findViewById(R.id.historiques_parties);
        gmanager.lancerPartie();
        historiqueView.setLayoutManager(new LinearLayoutManager(this));
        historiqueView.setAdapter( new CustomGridAdapterHistorique(gmanager.getHistorique()));

        bouton_retour_historique.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoriqueParties.this, Menu.class);
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
