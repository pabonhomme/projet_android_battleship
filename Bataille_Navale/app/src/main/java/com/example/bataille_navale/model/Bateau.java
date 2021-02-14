package com.example.bataille_navale.model;

public class Bateau {
    private int ligneDepart;
    private int colonneDepart;
    private Boolean direction; // false : horizontal; true : vertical
    private int taille;
    private Plateau plateau;

    /**
     * constructeur de Bateau
     */
    public Bateau(int taille, Plateau plateau){
        this.taille = taille;
        this.plateau = plateau;
    }

}
