package com.example.bataille_navale.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe qui permet de définir les parties
 */
public class Partie implements Serializable {

    /**
     * Id unique d'une partie
     */
    private UUID id = UUID.randomUUID();

    /**
     * Joueur gagnant de la partie
     */
    private Joueur jGagnant;

    /**
     * Joueur perdant de la partie
     */
    private Joueur jPerdant;

    /**
     * Constructeur d'une partie
     */
    public Partie() {
    }

    /**
     * Constructeur d'une partie en fonction d'un joueur gagnant et d'un joueur perdant
     *
     * @param jGagnant Joueur qui a gagné
     * @param jPerdant Joueur qui a perdu
     */
    public Partie(Joueur jGagnant, Joueur jPerdant) {
        setjGagnant(jGagnant);
        setjPerdant(jPerdant);
    }

    /**
     * Permet de mettre les scores de chaque joueur à jour
     */
    public void mettreScoreAJour() {
        for (Bateau bat : jGagnant.getPlateauAdverse().getNavires()) {
            if (bat.estCoule()) {
                jGagnant.incrementerScore();
            }
        }
        for (Bateau bat : jPerdant.getPlateauAdverse().getNavires()) {
            if (bat.estCoule()) {
                jPerdant.incrementerScore();
            }
        }
    }

    /**
     * Permet de retourner l'id de la partie
     *
     * @return Retourne un UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Permet de mettre à jour l'id de la partie
     *
     * @param id UUID
     */
    private void setId(UUID id) {
        this.id = id;
    }

    /**
     * Permet de retourner le joueur qui a gagné
     *
     * @return Retourne le joueur qui a gagné
     */
    public Joueur getjGagnant() {
        return jGagnant;
    }

    /**
     * Permet de mettre à jour le joueur qui a gagné
     *
     * @param jGagnant Nouvelle valeur du joueur qui a gagné
     */
    public void setjGagnant(Joueur jGagnant) {
        this.jGagnant = jGagnant;
    }

    /**
     * Permet de retourner le joueur qui a perdu
     *
     * @return Retourne le joueur qui a perdu
     */
    public Joueur getjPerdant() {
        return jPerdant;
    }

    /**
     * Permet de mettre à jour le joueur qui a perdu
     *
     * @param jPerdant Nouvelle valeur du joueur qui a perdu
     */
    public void setjPerdant(Joueur jPerdant) {
        this.jPerdant = jPerdant;
    }
}
