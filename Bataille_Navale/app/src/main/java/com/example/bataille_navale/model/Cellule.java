package com.example.bataille_navale.model;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

/**
 * Classe qui permet de définir les cellules
 */
public class Cellule {

    /**
     * Coordonnées de la cellule
     */
    private Pair<Integer, Integer> positions;

    /**
     * Navire contenu dans la cellule
     */
    private Bateau navire;

    /**
     * Boolean donnant l'état d'une cellule
     */
    private boolean estVisitee;

    /**
     * constructeur de Cellule
     */
    public Cellule(Pair<Integer, Integer> positions) {
        navire = null;
        estVisitee = false;
        setPositions(positions);
    }

    /**
     * Indique si la cellule a été touchée par un missile ou non
     *
     * @return boolean à true si elle a été touchée ou false sinon
     */
    public boolean estVisitee() {
        return estVisitee;
    }

    /**
     * Permet de positionner l'attribut estVisité à true pour indiquer que la cellule a été touchée par un missile
     */
    public void visite() {
        estVisitee = true;
        if (faitPartieBateau()) {
            navire.addCellTouchee();
        }
    }

    /**
     * Indique si la cellule contient un bateau ou non
     *
     * @return boolean à true si elle contient un bateau ou false sinon
     */
    public boolean faitPartieBateau() {
        return navire != null;
    }

    /**
     * Permet de placer un bateau dans cette cellule
     *
     * @param b Bateau
     */
    public void mettreBateau(Bateau b) {
        navire = b;
    }

    /**
     * Indique si la cellule contient un bateau touché
     *
     * @return boolean à true si elle contient un bateau touché, ou false sinon
     */
    public boolean estTouchee() {
        return faitPartieBateau() && estVisitee();
    }

    /**
     * Indique si la cellule contient un bateau coulé
     *
     * @return booleen à true si elle contient un bateau coulé, ou false sinon
     */
    public boolean estCoulee() {
        return navire.estCoule();
    }

    /***
     * Permet de retourner la position de la cellule
     * @return Retourne la position de la cellule
     */
    public Pair<Integer, Integer> getPositions() {
        return positions;
    }

    /**
     * Permet de mettre à jour la position de la cellule
     *
     * @param positions Nouvelle position de la cellule
     */
    public void setPositions(Pair<Integer, Integer> positions) {
        this.positions = positions;
    }

    /**
     * Permet de retourner le navire qui se trouve au niveau de la cellule
     *
     * @return Retourne le navire qui se trouve au niveau de la cellule
     */
    public Bateau getNavire() {
        return navire;
    }

    /**
     * Permet de mettre à jour le navire qui se trouve au niveau de la cellule
     *
     * @param navire Nouveau navire qui se trouve au niveau de la cellule
     */
    public void setNavire(Bateau navire) {
        this.navire = navire;
    }

    @NonNull
    @Override
    public String toString() {
        if (!estVisitee()) {
            return "";
        } else return "X";
    }
}
