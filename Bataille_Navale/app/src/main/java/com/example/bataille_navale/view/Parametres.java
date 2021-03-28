package com.example.bataille_navale.view;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bataille_navale.R;
import com.example.bataille_navale.manager.MusicManager;


public class Parametres extends AppCompatActivity {

    private final MusicManager gMusique = MusicManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametres);

        gMusique.putMusic(getApplicationContext(), R.raw.musique_menu);

        SeekBar barre_volume = findViewById(R.id.barre_volume);
        int volumeProgress = (int) (gMusique.getVolume() * 100f);
        barre_volume.setProgress(volumeProgress);
        barre_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f; // on divise par 100 la valeur de la SeekBar car volume compris entre 0 et 1
                gMusique.changerVolume(volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        gMusique.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gMusique.putMusic(getApplicationContext(), R.raw.musique_menu);
        super.onResume();
    }
}
