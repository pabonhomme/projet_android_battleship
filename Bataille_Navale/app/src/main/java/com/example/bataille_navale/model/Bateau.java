package com.example.bataille_navale.model;

import androidx.core.util.Pair;

public class Bateau {

    private Pair<Integer, Integer> positions;
    private Orientation direction; // false : horizontal; true : vertical
    private int taille;

    /**
     * constructeur de Bateau
     */
    public Bateau(int taille){
        setTaille(taille);
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Orientation getDirection() {
        return direction;
    }

    public void setDirection(Orientation direction) {
        this.direction = direction;
    }

    public Pair<Integer, Integer> getPositions() {
        return positions;
    }

    public void setPositions(Pair<Integer, Integer> positions) {
        this.positions = positions;
    }
}
