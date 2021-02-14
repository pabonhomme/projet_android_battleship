package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.Manager;

public class Menu extends AppCompatActivity {

    private Manager manager = Manager.getInstance();

    AlphaAnimation animation=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button button_menu_jouer = findViewById(R.id.menu_jouer);
        button_menu_jouer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Button button_menu_parametres = findViewById(R.id.menu_parametres);

                if(button_menu_parametres.getVisibility() == View.VISIBLE){ // test des animations sur le bouton paramètre à partir du bouton jouer
                    animation = new AlphaAnimation(1, 0);
                    animation.setDuration(500);
                    animation.setInterpolator(new AccelerateInterpolator());
                    button_menu_parametres.startAnimation(animation);
                    button_menu_parametres.setVisibility(View.INVISIBLE);
                    button_menu_parametres.setClickable(false);
                }
                else{
                    animation = new AlphaAnimation(0, 1);
                    animation.setDuration(500);
                    animation.setInterpolator(new AccelerateInterpolator());
                    button_menu_parametres.startAnimation(animation);
                    button_menu_parametres.setVisibility(View.VISIBLE);
                    button_menu_parametres.setClickable(true);
                }
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
