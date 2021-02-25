package com.example.bataille_navale.model;

public class Bateau {
    private int ligneDepart;
    private int colonneDepart;
    private Boolean direction; // false : horizontal; true : vertical
    private int taille;
//    private Plateau plateau;

    /**
     * constructeur de Bateau
     */
    public Bateau(int taille){
        setTaille(taille);
//        this.plateau = plateau;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getLigneDepart() {
        return ligneDepart;
    }

    public void setLigneDepart(int ligneDepart) {
        this.ligneDepart = ligneDepart;
    }

    public int getColonneDepart() {
        return colonneDepart;
    }

    public void setColonneDepart(int colonneDepart) {
        this.colonneDepart = colonneDepart;
    }
}
