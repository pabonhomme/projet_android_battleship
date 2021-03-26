package com.example.bataille_navale.persistance;


import com.example.bataille_navale.model.Partie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Gere la serialization d'une collection de Partie
 */
public abstract class Sauveur {

    /**
     * Sauvegarde une collection de Partie
     *
     * @param historique La collection a sauvegarder
     */
    public abstract void sauvegarderStats(List<Partie> historique, FileOutputStream file);

    /**
     * Charge une collection de Partie
     *
     * @return La collection de Partie
     */
    public abstract List<Partie> chargerStats(FileInputStream file);
}

