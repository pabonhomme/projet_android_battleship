package com.example.bataille_navale.model;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

public class Cellule {

    private Pair<Integer, Integer> positions;
    private Bateau navire;
    private boolean estVisitee;

    /**
     * constructeur de Cellule
     */
    public Cellule(Pair<Integer, Integer> positions){
        navire = null;
        estVisitee = false;
        setPositions(positions);
    }

    /**
     * Indique si la cellule a été touchée par un missile ou non
     * @return boolean
     */
    public boolean estVisitee(){
        return estVisitee;
    }

    /**
     * Permet de positionner l'attribut estVisité à true pour indiquer que la cellule a été touchée par un missile
     */
    public void visite(){
        estVisitee = true;
        if(faitPartieBateau()){
            navire.addCellTouchee();
        }
    }

    /**
     * Indique si la cellule contient un bateau ou non
     * @return boolean
     */
    public boolean faitPartieBateau(){
        return navire != null;
    }

    /**
     * Permet de placer un bateau dans cette cellule
     * @param b Bateau
     */
    public void mettreBateau(Bateau b){
        navire = b;
    }

    /**
     * Indique si la cellule contient un bateau touché
     * @return boolean
     */
    public boolean estTouchee(){
        return faitPartieBateau() && estVisitee();
    }

    public Pair<Integer, Integer> getPositions() {
        return positions;
    }

    public void setPositions(Pair<Integer, Integer> positions) {
        this.positions = positions;
    }

    @NonNull
    @Override
    public String toString() {
        if(!estVisitee()){
            return "";
        }
        else return "X";
    }
}
