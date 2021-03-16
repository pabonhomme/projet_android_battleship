package com.example.bataille_navale.model;

import java.io.Serializable;

/**
 * Classe qui permet de définir les parties
 */
public class Partie implements Serializable {

    private Joueur jGagnant;
    private Joueur jPerdant;

    /**
     * Constructeur d'une partie
     */
    public Partie() {
    }

    /**
     * Constructeur d'une partie en fonction d'un joueur gagnant et d'un joueur perdant
     * @param jGagnant Joueur qui a gagné
     * @param jPerdant Joueur qui a perdu
     */
    public Partie(Joueur jGagnant, Joueur jPerdant) {
        setjGagnant(jGagnant);
        setjPerdant(jPerdant);
    }

    /**
     * Permet de retourner le joueur qui a gagné
     * @return Retourne le joueur qui a gagné
     */
    public Joueur getjGagnant() {
        return jGagnant;
    }

    /**
     * Permet de mettre à jour le joueur qui a gagné
     * @param jGagnant Nouvelle valeur du joueur qui a gagné
     */
    public void setjGagnant(Joueur jGagnant) {
        this.jGagnant = jGagnant;
    }

    /**
     * Permet de retourner le joueur qui a perdu
     * @return Retourne le joueur qui a perdu
     */
    public Joueur getjPerdant() {
        return jPerdant;
    }

    /**
     * Permet de mettre à jour le joueur qui a perdu
     * @param jPerdant Nouvelle valeur du joueur qui a perdu
     */
    public void setjPerdant(Joueur jPerdant) {
        this.jPerdant = jPerdant;
    }
}
