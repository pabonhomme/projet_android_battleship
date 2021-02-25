package com.example.bataille_navale.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bataille_navale.R;
import com.example.bataille_navale.adapter.CustomGridAdapter;
import com.example.bataille_navale.model.Manager;

public class Placement_Bateau extends AppCompatActivity {

    Manager manager = Manager.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_bateau);

        RecyclerView view = findViewById(R.id.gridView);
        manager.lancerPartie();
        view.setLayoutManager(new GridLayoutManager(this, 2));

        view.setAdapter( new CustomGridAdapter(manager.getPartie().getPlateauJ1().getGrille()));



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
