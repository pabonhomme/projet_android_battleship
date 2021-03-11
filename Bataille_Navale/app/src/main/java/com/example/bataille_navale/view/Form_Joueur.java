package com.example.bataille_navale.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bataille_navale.R;
import com.example.bataille_navale.model.GameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Form_Joueur extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    private TextView inserez_pseudo_text = null;
    private EditText pseudo_joueur_form = null;
    private Button bouton_suivant_form = null;

    private Button boutonPhoto = null;
    private ImageView imageJoueur = null;

    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_infos_joueur);

        inserez_pseudo_text = findViewById(R.id.inserez_pseudo_text);
        pseudo_joueur_form = findViewById(R.id.pseudo_joueur_form);
        bouton_suivant_form = findViewById(R.id.bouton_suivant_form);
        boutonPhoto = findViewById(R.id.button_image);
        imageJoueur = findViewById(R.id.imageJoueur);

        bouton_suivant_form.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!pseudo_joueur_form.getText().toString().equals("")) {
                    if (gmanager.getJoueurEnCours() == gmanager.getJ1()) {
                        gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                        inserez_pseudo_text.setText("Joueur 2 : veuillez rentrer votre pseudo");
                        pseudo_joueur_form.setText("");
                        imageJoueur.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
                        gmanager.changementJoueur();
                    } else {
                        gmanager.getJoueurEnCours().setPseudo(pseudo_joueur_form.getText().toString());
                        gmanager.changementJoueur();
                        Intent intent = new Intent(Form_Joueur.this, Placement_Bateau.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(Form_Joueur.this, "Vous n'avez pas rentré votre pseudo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        boutonPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

    }

    private void captureImage() {
        // On crée un intent pour la caméra
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // On lance la caméra et on attend pour les résultats
        this.startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
    }

    // When results returned
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
//                ecrire
//                File photos = new File(getApplicationContext().getFilesDir(), "photos");
//                File file = new File(photos, "toto.png");
//
//                FileOutputStream fos = null;
//                try {
//                    fos = new FileOutputStream(file);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                bp.compress(Bitmap.CompressFormat.JPEG, 75, fos);
//
////                lire
//                Bitmap bp2 = BitmapFactory.decodeFile(file.getAbsolutePath());

                gmanager.getJoueurEnCours().setImageJoueur(bp);
                this.imageJoueur.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action supprimée", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action ratée", Toast.LENGTH_LONG).show();
            }
        }
    }


//    private void permissionPhoto() {
//
//        // With Android Level >= 23, you have to ask the user
//        // for permission to read/write data on the device.
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//
//            // Check if we have read/write permission
//            int readPermission = ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE);
//            int writePermission = ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//            if (writePermission != PackageManager.PERMISSION_GRANTED ||
//                    readPermission != PackageManager.PERMISSION_GRANTED) {
//                // If don't have permission so prompt the user.
//                this.requestPermissions(
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_ID_READ_WRITE_PERMISSION
//                );
//                return;
//            }
//        }
//        this.captureImage();
//    }
//
////     When you have the request results
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //
//        switch (requestCode) {
//            case REQUEST_ID_READ_WRITE_PERMISSION: {
//
//                // Note: If request is cancelled, the result arrays are empty.
//                // Permissions granted (read/write).
//                if (grantResults.length > 1
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(this, "Permission accordée!", Toast.LENGTH_LONG).show();
//
//                    this.captureImage();
//
//                }
//                // Cancelled or denied.
//                else {
//                    Toast.makeText(this, "Permission refusée!", Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
