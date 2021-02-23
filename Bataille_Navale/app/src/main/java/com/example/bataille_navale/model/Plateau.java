package com.example.bataille_navale.model;

import androidx.annotation.NonNull;

public class Plateau {

    /*--- définition des attributs du plateau ---*/
    public static final int TAILLE_PLATEAU = 10;
    public static final boolean VERTICAL = false;
    public static final boolean HORIZONTAL = true;
    public static final int [] PLACEMENT = {2,2,1,1}; // temporairement on a 4 bateaux
//    public static final int [] PLACEMENT = {5,4,3,2,2,1,1};
    Bateau [] navires = new Bateau[PLACEMENT.length]; // on crée un tableau de PLACEMENT.length bâteaux
    Cellule [] [] grille = new Cellule[TAILLE_PLATEAU][TAILLE_PLATEAU]; // on crée une grille de 100 cases

    /**
     * constructeur de Plateau
     */
    public Plateau(){
        creeGrille();
        creerNavires();
    }

    /**
     * on crée les cellules du plateau de jeu
     */
    public void creeGrille() {
        for (int ligne = 0; ligne < TAILLE_PLATEAU; ligne++) {
            for (int colonne = 0; colonne < TAILLE_PLATEAU; colonne++) {
                grille[ligne][colonne] = new Cellule();
            }
        }
    }

    /**
     * on place les navires, PLACEMENT[] donne la taille de chaque Bateau au constructeur
     */
    public void creerNavires() {
        for (int i = 0; i < PLACEMENT.length; i++)
            navires[i] = new Bateau(PLACEMENT[i], this);
    }

    /**
     * retourne la cellule aux ligne et colonne passées en paramètres
     * @param ligne int
     * @param colonne int
     * @return Cellule la cellule demandée ou null si la cellule n'existe pas
     */
    public Cellule getCellule(int ligne, int colonne){
        if ((ligne >= 0 && ligne < TAILLE_PLATEAU) && (colonne >= 0 && colonne < TAILLE_PLATEAU))
            return grille[ligne][colonne];
        return null;

    }

    /**
     * Vérifie si on peut placer un bateau ou non à la position donnée
     * @param ligneVoulue ligne de départ du bateau
     * @param colonneVoulue colonne de départ du bateau
     * @param direction direction du bateau VERTICAL | HORIZONTAL
     * @return boolean
     */
    public boolean estLibre(int ligneVoulue, int colonneVoulue, boolean direction, int taille) {

        /*--- premier cas direction horizontal ---*/
        if (direction == HORIZONTAL){
            for (int colonneBis = colonneVoulue; colonneBis <= colonneVoulue + taille - 1; colonneBis++) {
                if (getCellule(ligneVoulue, colonneBis) != null) {
                    if (grille[ligneVoulue][colonneBis].faitPartieBateau()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
            /*--- deuxième cas VERTICAL ---*/
        } else {for (int ligneBis = ligneVoulue; ligneBis <= ligneVoulue + taille - 1; ligneBis++){
            if (getCellule(ligneBis,colonneVoulue) != null) {
                if (grille[ligneBis][colonneVoulue].faitPartieBateau()) {
                    return false;
                }
            } else {
                return false;
            }
        }
            return true;
        }
    }

    /**
     * on positionne le bateau à une position donnée
     * @param ligneDepart int ligne sur laquelle le bateau sera positionné
     * @param colonneDepart int colonne sur laquelle le bateau sera positionné
     * @param direction boolean direction du bateau
     * @param taille int taille du bateau
     * @param bateau Bateau bateau à mettre
     */
    public void positionneBateau(int ligneDepart, int colonneDepart, boolean direction, int taille, Bateau bateau) {

        if (direction == Plateau.HORIZONTAL)
            for (int newColonne = colonneDepart; newColonne <= colonneDepart + taille - 1; newColonne++)
                grille[ligneDepart][newColonne].mettreBateau(bateau);
        else
            for (int newLigne = ligneDepart; newLigne <= ligneDepart + taille - 1; newLigne++)
                grille[newLigne][colonneDepart].mettreBateau(bateau);

    }

    /**
     * Vérifie si la cellule contient un bateau
     * @param ligne int ligne à vérifier
     * @param colonne int colonne à vérifier
     * @return boolean, true si bateau false sinon
     */
    public boolean faitPartieDeBateau(int ligne, int colonne){
        Cellule cel = getCellule(ligne, colonne);
        if(cel != null){
            return cel.faitPartieBateau();
        }
        return false;
    }

    /**
     * Vérifie si la cellule aux coordonnées ligne, colonne a été touchée par un missile
     * @param ligne int ligne à vérifier
     * @param colonne int colonne à vérifier
     * @return boolean, true si visitée false sinon
     */
    public boolean estVisitee(int ligne, int colonne) {
        Cellule cel = getCellule(ligne, colonne);
        if(cel != null){
            return cel.estVisitee();
        }
        return false;
    }

    /**
     * Vérifie si la cellule aux coordonnées ligne, colonne a été coulée
     * @param ligne int ligne à vérifier
     * @param colonne int colonne à vérifier
     * @return boolean, true si coulée false sinon
     */
    public boolean estCoulee(int ligne, int colonne){
        Cellule cel = getCellule(ligne, colonne);
        if(cel != null){
            return cel.estCoulee();
        }
        return false;
    }

    /**
     * Tire un missile sur la cellule à la position ligne, colonne
     * @param ligne int ligne à toucher
     * @param colonne int colonne à toucher
     */
    public void visite(int ligne, int colonne){

        Cellule cel = getCellule(ligne, colonne);
        if(cel != null){
            cel.visite();
        }
    }

    /**
     * Vérifie l'état des bateaux sur le plateau de jeu
     * @return boolean, true si tous les bateaux ont été touchés false sinon
     */
    public boolean etatsBateaux(){
        for(int ligneBis = 0; ligneBis < Plateau.TAILLE_PLATEAU; ligneBis++)
            for(int colonneBis = 0; colonneBis < Plateau.TAILLE_PLATEAU; colonneBis++)
                if (grille[ligneBis][colonneBis].faitPartieBateau() &&
                        !grille[ligneBis][colonneBis].estVisitee())
                    return false;
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String retour = "";
        for (int ligne = 0; ligne < TAILLE_PLATEAU; ligne++) {
            for (int colonne = 0; colonne < TAILLE_PLATEAU; colonne++) {
                retour += grille[ligne][colonne].toString();
            }
            retour += "";
        }
        return retour;
    }
}