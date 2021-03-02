package com.example.bataille_navale.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.CustomGridAdapterDeux;
import com.example.bataille_navale.model.Cellule;
import com.example.bataille_navale.model.GameManager;

public class Placement_Bateau extends AppCompatActivity {

    GameManager gmanager = GameManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_bateau);

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
                cell.visite();
                if(cell.estTouchee()) {
                    background = getResources().getDrawable(R.drawable.coule); // si le bateau est touchée
                }
                else {
                     background = getResources().getDrawable( R.drawable.plouf); // si y'a pas de bateau
                }
                leTextView.setBackground(background);

                // This tells the GridView to redraw itself
                // in turn calling your CustomGridAdapterDeux's getView method again for each cell
                gridAdapter.notifyDataSetChanged();
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
