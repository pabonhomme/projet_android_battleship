package com.example.bataille_navale.model;

import androidx.core.util.Pair;

/**
 * Classe qui permet de définir les bateaux
 */
public class Bateau {

    /**
     * Coordonées des bateaux
     */
    private Pair<Integer, Integer> positions;

    /**
     * Orientation des bateaux
     * false : horizontal; true : vertical
     */
    private Orientation direction;

    /**
     * Taille du bateau
     */
    private int taille;

    /**
     * Nombre de cellule touchées du bateau
     */
    private int cellTouchees = 0;

    /**
     * Constructeur de Bateau
     *
     * @param ligne     Ligne du bateau
     * @param colonne   Colonne du bateau
     * @param direction Direction du bateau
     * @param taille    Taille (nombre de cases) du bateau
     */
    public Bateau(int ligne, int colonne, Orientation direction, int taille) {
        setPositions(new Pair<>(ligne, colonne));
        setDirection(direction);
        setTaille(taille);
    }

    /**
     * Permet de savoir si le bateau est coulée
     *
     * @return boolean si il est coulé
     */
    public boolean estCoule() {
        return cellTouchees == taille;
    }

    /**
     * Ajout une partie du bateau touchée au compteur
     */
    public void addCellTouchee() {
        if (cellTouchees < taille) {
            cellTouchees++;
        }
    }

    /**
     * Permet de retourner la taille du bateau
     *
     * @return Retourne la taille du bateau
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Permet de mettre à jour la taille du bateau
     *
     * @param taille Nouvelle taille du bateau
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Permet de retourner la direction du bateau
     *
     * @return Retourne la direction du bateau
     */
    public Orientation getDirection() {
        return direction;
    }

    /**
     * Permet de mettre à jour la direction du bateau
     *
     * @param direction Nouvelle direction du bateau
     */
    public void setDirection(Orientation direction) {
        this.direction = direction;
    }

    /**
     * Permet de retourner la position du bateau
     *
     * @return Retourne la position du bateau
     */
    public Pair<Integer, Integer> getPositions() {
        return positions;
    }

    /**
     * Permet de mettre à jour la position du bateau
     *
     * @param positions Nouvelle position du bateau
     */
    public void setPositions(Pair<Integer, Integer> positions) {
        this.positions = positions;
    }
}
