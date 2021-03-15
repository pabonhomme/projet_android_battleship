package com.example.bataille_navale.sauveurs.stub;

import com.example.bataille_navale.model.Joueur;
import com.example.bataille_navale.model.Partie;
import com.example.bataille_navale.sauveurs.Sauveur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simule la serialization d'une collection de joueur
 */
public class Stub extends Sauveur {

    /**
     * Simule la sauvegarde d'une collection de joueur
     * @param lesJoueurs  La collection a sauvegarder
     */
    @Override
    public void sauvegarderStats(List<Partie> lesJoueurs, FileOutputStream file) {

    }

    /**
     * Charge une collection de joueur
     * @return  La collection de joueur
     */
    @Override
    public List<Partie> chargerStats(FileInputStream file) {
        List<Partie> historique = new ArrayList<Partie>();
        historique.add(new Partie(new Joueur("Paul", 5), new Joueur("Noé", 3)));
        historique.add(new Partie(new Joueur("Théo", 5), new Joueur("Noé", 3)));
        historique.add(new Partie(new Joueur("Marie", 5), new Joueur("Noé", 3)));
        historique.add(new Partie(new Joueur("Chloé", 5), new Joueur("Noé", 3)));
        historique.add(new Partie(new Joueur("Ambroise", 5), new Joueur("Noé", 3)));
        historique.add(new Partie(new Joueur("Sami", 5), new Joueur("Noé", 3)));
        return historique;
    }
}
