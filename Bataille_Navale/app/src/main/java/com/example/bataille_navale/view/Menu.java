package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.GameManager;

public class Menu extends FragmentActivity {

    private GameManager gmanager = GameManager.getInstance();

    AlphaAnimation animation=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button button_menu_jouer = findViewById(R.id.menu_jouer);
        button_menu_jouer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gmanager.lancerPartie();
                Intent intent = new Intent(Menu.this, Placement_Bateau.class);
                startActivity(intent);
                finish();
            }
        });

        Button button_menu_retour = findViewById(R.id.menu_retour);
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
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
