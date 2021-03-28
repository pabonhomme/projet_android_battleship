package com.example.bataille_navale.manager;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.RawRes;

/**
 * Classe qui permet de gérer la musique de l'application
 */
public class MusicManager {
    private static MusicManager instanceUnique;
    private int musiqueActuelle;
    private MediaPlayer mp;

    /**
     * Constructeur
     */
    private MusicManager() {

    }

    /**
     * Méthode qui vérifie qu'il n'y ai pas d'instance de GameManager déjà créée, si non alors elle l'instancie
     *
     * @return le game manager unique ou non
     */
    public static MusicManager getInstance() {
        if (instanceUnique == null) {
            instanceUnique = new MusicManager();
        }
        return instanceUnique;
    }

    /**
     * Permet de vérifier que la musique à mettre n'est pas celle déjà enregistrée en tant que musique actuelle
     * @param context Context contexte de l'application
     * @param id int id de la musique à mettre
     */
    public void putMusic(Context context, @RawRes int id) {
        if (musiqueActuelle != id) {
            play(context, id);
        } else {
            startMusic();
        }
    }

    /**
     * Permet de lancer une nouvelle musique
     * @param context Context contexte de l'application
     * @param id int id de la musique à mettre
     */
    private void play(Context context, @RawRes int id) {
        if (mp != null) {
            mp.stop();
        }
        musiqueActuelle = id;
        mp = MediaPlayer.create(context, id);
        mp.setLooping(true);
        mp.seekTo(0);
        startMusic();
    }

    /**
     * Permet de lancer la musique
     */
    private void startMusic() {
        if (mp != null) {
            mp.start();
        }
    }

    /**
     * Permet de mettre en pause la musique
     */
    public void pauseMusic() {
        if (mp != null) {
            mp.pause();
        }
    }

    /**
     * Permet de stopper la musique et supprimer la musique actuelle
     */
    public void stopMusiq() {
        if (mp != null) {
            mp.stop();
            musiqueActuelle = 0;
        }
    }
}
