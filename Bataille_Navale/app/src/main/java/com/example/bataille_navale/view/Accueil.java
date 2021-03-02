package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;

public class Accueil extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_accueil);

        Button button = findViewById(R.id.accueil_commencer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Accueil.this, Plateau_Jeu.class);
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
