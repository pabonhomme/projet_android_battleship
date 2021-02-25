package com.example.bataille_navale.model;

import androidx.annotation.NonNull;

public class Cellule {

    private Bateau navire;
    private boolean estVisitee;

    /**
     * constructeur de Cellule
     */
    public Cellule(){
        navire = null;
        estVisitee = false;
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
     * Indique si le navire est coulé
     * @return boolean
     */
    public boolean estCoulee(){
        return faitPartieBateau() && estVisitee();
    }

    @NonNull
    @Override
    public String toString() {
        if(!estVisitee()){
            return "O";
        }
        else if(faitPartieBateau()){
            return "X";
        }
        return "°";
    }
}
