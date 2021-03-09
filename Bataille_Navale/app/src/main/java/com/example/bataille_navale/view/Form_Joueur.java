package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.GameManager;

public class Form_Joueur extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView inserez_pseudo_text = null;
    private EditText pseudo_joueur_form = null;
    private Button bouton_suivant_form = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_infos_joueur);

        inserez_pseudo_text =  findViewById(R.id.inserez_pseudo_text);
        pseudo_joueur_form =  findViewById(R.id.pseudo_joueur_form);
        bouton_suivant_form = findViewById(R.id.bouton_suivant_form);

        bouton_suivant_form.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!pseudo_joueur_form.getText().toString().equals("")){
                   if(gmanager.getJoueurEnCours() == gmanager.getJ1()){
                       gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                       pseudo_joueur_form.setText("");
                       inserez_pseudo_text.setText("Joueur 2 : veuillez rentrer votre pseudo");
                       gmanager.setJoueurEnCours(gmanager.getJ2());
                   }
                   else{
                       gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                       gmanager.setJoueurEnCours(gmanager.getJ1());
                       Intent intent = new Intent(Form_Joueur.this, Placement_Bateau.class);
                       startActivity(intent);
                       finish();
                   }
                }
                else{
                    Toast.makeText(Form_Joueur.this, "Vous n'avez pas rentré votre pseudo", Toast.LENGTH_SHORT).show();
                }
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
