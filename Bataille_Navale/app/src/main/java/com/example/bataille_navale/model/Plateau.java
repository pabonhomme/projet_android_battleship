package com.example.bataille_navale.model;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import java.util.ArrayList;

import static com.example.bataille_navale.model.Orientation.HORIZONTAL;

public class Plateau {

    /*--- définition des attributs du plateau ---*/
    public static final int TAILLE_PLATEAU = 10;
    public static final int [] TAILLE_NAVIRES = {5,4,3,2,2,1,1}; // temporairement on a 4 bateaux
    public static final int NB_BATEAUX = TAILLE_NAVIRES.length;
//    private final Bateau [] navires = new Bateau[PLACEMENT.length]; // on crée un tableau de PLACEMENT.length bâteaux
    private ArrayList<Bateau> navires = new ArrayList<>();
    private ArrayList<Cellule> grille = new ArrayList<>(); // on crée une grille de 100 cases

    /**
     * constructeur de Plateau
     */
    public Plateau(){
        creeGrille();
//        creerNavires();
    }

    /**
     * on crée les cellules du plateau de jeu
     */
    public void creeGrille() {
        for (int ligne = 0; ligne < TAILLE_PLATEAU; ligne++) {
            for (int colonne = 0; colonne < TAILLE_PLATEAU; colonne++) {
                grille.add(new Cellule(new Pair<>(ligne, colonne)));
            }
        }
    }

    /**
     * on crée les navires en leur donnant une taille, PLACEMENT[] donne la taille de chaque Bateau au constructeur
     */
//    public void creerNavires() {
//        for (int i = 0; i < PLACEMENT.length; i++)
//            navires[i] = new Bateau(PLACEMENT[i]);
//    }

    /**
     * retourne la cellule aux ligne et colonne passées en paramètres
     * @param ligne int
     * @param colonne int
     * @return Cellule la cellule demandée ou null si la cellule n'existe pas
     */
    public Cellule getCellule(int ligne, int colonne){
        if ((ligne >= 0 && ligne < TAILLE_PLATEAU) && (colonne >= 0 && colonne < TAILLE_PLATEAU)){
            for (Cellule cell: grille) {
                if(cell.getPositions().first == ligne && cell.getPositions().second == colonne)
                    return cell;
            }
        }
        return null;

    }

    /**
     * Vérifie si on peut placer un bateau ou non à la position donnée
     * @param ligneVoulue ligne de départ du bateau
     * @param colonneVoulue colonne de départ du bateau
     * @param direction direction du bateau VERTICAL | HORIZONTAL
     * @return boolean
     */
    public boolean estLibre(int ligneVoulue, int colonneVoulue, Orientation direction, int taille) {

        /*--- premier cas direction horizontal ---*/
        if (direction == HORIZONTAL){
            for (int colonneBis = colonneVoulue; colonneBis <= colonneVoulue + taille - 1; colonneBis++) {
                if(faitPartieDeBateau(ligneVoulue, colonneBis) || colonneBis >= TAILLE_PLATEAU){
                    return false;
                }
            }
            return true;
            /*--- deuxième cas VERTICAL ---*/
        } else {
            for (int ligneBis = ligneVoulue; ligneBis <= ligneVoulue + taille - 1; ligneBis++){
                if(faitPartieDeBateau(ligneBis, colonneVoulue) || ligneBis >= TAILLE_PLATEAU){
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Permet de placer un bateau sur la grille
     * @param bateau Bateau bateau à possionner
     * @return true si possible, non si pas la place
     */
    public boolean positionneBateau(Bateau bateau) {

        if(estLibre(bateau.getPositions().first, bateau.getPositions().second, bateau.getDirection(), bateau.getTaille())){ // on vérifie que la place pour le bateau est libre
            if (bateau.getDirection() == HORIZONTAL){
                for (int newColonne = bateau.getPositions().second; newColonne <= bateau.getPositions().second + bateau.getTaille() - 1; newColonne++){
                    getCellule(bateau.getPositions().first, newColonne).mettreBateau(bateau);
                }
                navires.add(bateau);
                return true; // si on a bien positionné le bateau
            }
            else{
                for (int newLigne = bateau.getPositions().first; newLigne <= bateau.getPositions().first + bateau.getTaille() - 1; newLigne++){
                    getCellule(newLigne, bateau.getPositions().second).mettreBateau(bateau);
                }
                navires.add(bateau);
                return true; // si on a bien positionné le bateau
            }
        }
        return false; // pas libre donc pas possible de placer
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
    public boolean estTouchee(int ligne, int colonne){
        Cellule cel = getCellule(ligne, colonne);
        if(cel != null){
            return cel.estTouchee();
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

    public int nombreBateauxCoules(){
        int i = 0;
        for (Bateau bat:navires) {
            if(bat.estCoule()){
                i++;
            }
        }
        return i;
    }

    /**
     * Vérifie l'état des bateaux sur le plateau de jeu
     * @return boolean, true si tous les bateaux ont été touchés false sinon
     */
    public boolean isPlateauCoule(){
        int i = 0;
        for(Bateau bat:navires) {
            if(bat.estCoule())
                i++;
        }
        return i == NB_BATEAUX;
    }

    @NonNull
    @Override
    public String toString() {
        String retour = "";
        for (int ligne = 0; ligne < TAILLE_PLATEAU; ligne++) {
            for (int colonne = 0; colonne < TAILLE_PLATEAU; colonne++) {
                retour += getCellule(ligne, colonne).toString();
            }
            retour += "";
        }
        return retour;
    }

    public ArrayList<Bateau> getNavires() {
        return navires;
    }

    public ArrayList<Cellule> getGrille() {
        return grille;
    }
}
