package com.example.bataille_navale.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.manager.GameManager;

public class FormJoueur extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView inserez_pseudo_text = null;
    private EditText pseudo_joueur_form = null;

    private ImageView imageJoueur = null;

    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_infos_joueur);

        inserez_pseudo_text = findViewById(R.id.inserez_pseudo_text);
        pseudo_joueur_form = findViewById(R.id.pseudo_joueur_form);
        Button bouton_suivant_form = findViewById(R.id.bouton_suivant_form);
        Button bouton_retour_form = findViewById(R.id.bouton_retour_form);
        Button boutonPhoto = findViewById(R.id.button_image);
        imageJoueur = findViewById(R.id.imageJoueur);

        imageJoueur.setImageDrawable(getResources().getDrawable(R.drawable.image_joueur_base));

        if(savedInstanceState!= null){
            if(savedInstanceState.getParcelable("imageJoueur") != null){
                imageJoueur.setImageBitmap(savedInstanceState.getParcelable("imageJoueur"));
            }
            else{
                imageJoueur.setImageDrawable(getResources().getDrawable(R.drawable.image_joueur_base));
            }
        }

        bouton_suivant_form.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!pseudo_joueur_form.getText().toString().equals("")) {
                    if (gmanager.getJoueurEnCours() == gmanager.getJ1()) {
                        gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                        inserez_pseudo_text.setText(R.string.joueur2_rentrer_pseudo);
                        pseudo_joueur_form.setText("");
                        imageJoueur.setImageDrawable(getResources().getDrawable(R.drawable.image_joueur_base));
                        gmanager.changementJoueur();
                    } else {
                        gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                        gmanager.changementJoueur();
                        Intent intent = new Intent(FormJoueur.this, PlacementBateau.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(FormJoueur.this, R.string.erreur_pseudo_non_rentre, Toast.LENGTH_SHORT).show();
                }
            }
        });

        boutonPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        bouton_retour_form.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormJoueur.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if(gmanager.getJoueurEnCours().getImageJoueur() != null){
            outState.putParcelable("imageJoueur", gmanager.getJoueurEnCours().getImageJoueur());
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Permet de prendre une image en photo
     */
    private void captureImage() {
        // On crée un intent pour la caméra
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // On lance la caméra et on attend pour les résultats
        this.startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
    }

    /**
     * Quand les résultats sont retournés
     *
     * @param requestCode int
     * @param resultCode  int
     * @param data        int
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                gmanager.getJoueurEnCours().setImageJoueur(bp);
                this.imageJoueur.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.action_supprimee, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.action_ratee, Toast.LENGTH_LONG).show();
            }
        }
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
