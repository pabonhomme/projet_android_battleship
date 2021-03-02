package com.example.bataille_navale.model;

import androidx.core.util.Pair;

public class Bateau {

    private Pair<Integer, Integer> positions;
    private Orientation direction; // false : horizontal; true : vertical
    private int taille;
    private int cellTouchees = 0;
    private boolean coule = false;

    /**
     * constructeur de Bateau
     */
    public Bateau(int taille){
        setTaille(taille);
    }

    public Bateau(int ligne, int colonne, Orientation direction, int taille){
        setPositions(new Pair<>(ligne, colonne));
        setDirection(direction);
        setTaille(taille);
    }

    /**
     * Permet de savoir si le bateau est coulée
     * @return boolean si il est coulé
     */
    public boolean estCoule(){
        return cellTouchees == taille;
    }

    /**
     * Ajout une partie du bateau touchée au compteur
     */
    public void addCellTouchee(){
        if(cellTouchees <taille){
            cellTouchees++;
        }
        if(estCoule()){
            coule = true;
        }
    }

    public boolean isCoule() {
        return coule;
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
