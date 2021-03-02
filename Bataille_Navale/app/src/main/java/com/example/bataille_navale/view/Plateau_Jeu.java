package com.example.bataille_navale.view;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.CustomGridAdapterDeux;
import com.example.bataille_navale.model.Cellule;
import com.example.bataille_navale.model.GameManager;

public class Plateau_Jeu extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();
    AlphaAnimation animation=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plateau_jeu);

        gmanager.lancerPartie();

        final GridView gridView = findViewById(R.id.gridView); // on récupère la grid
        final CustomGridAdapterDeux gridAdapter = new CustomGridAdapterDeux(this, gmanager.getJ1().getPlateau().getGrille()); // on set l'adapter
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                // quand on clique sur une case on change la couleur de la case
                TextView leTextView = view.findViewById(R.id.cellule); // on récupère la textView

                Cellule cell = gmanager.getJ1().getPlateau().getGrille().get(position);
                Drawable background;

                if(!cell.estVisitee()){
                    cell.visite();
                    if(cell.estCoulee()){
                        background = getResources().getDrawable( R.drawable.touche); // si le bateau est touchée
                    }
                    else {
                        background = getResources().getDrawable( R.drawable.plouf); // si y'a pas de bateau
                    }
                    touchAnimation(leTextView, background);
                }
                else{
                    Toast.makeText(Plateau_Jeu.this, "La case en " + cell.getPositions().first +","+ cell.getPositions().second+" est déjà touchée",
                            Toast.LENGTH_SHORT).show();
                }

                // This tells the GridView to redraw itself
                // in turn calling your CustomGridAdapterDeux's getView method again for each cell
                gridAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Change le fond de la cellule avec une animation
     * @param leTextView Textview
     * @param background
     */
    private void touchAnimation(TextView leTextView, Drawable background){
        animation = new AlphaAnimation(0, 1);
        animation.setDuration(100);
        animation.setInterpolator(new AccelerateInterpolator());
        leTextView.startAnimation(animation);
        leTextView.setBackground(background);
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
