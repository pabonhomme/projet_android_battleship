package com.example.bataille_navale.model;


import android.graphics.Bitmap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe qui permet de définir les joueurs
 */
public class Joueur implements Comparable<Joueur>, Serializable {

    private String pseudo;
    private int score;
    private transient Bitmap imageJoueur = null;
    private transient Plateau plateau;
    private transient Plateau plateauAdverse;


    /**
     * Creer un Joueur
     */
    public Joueur(){
        setPseudo("");
        setScore(0);
        setPlateau(new Plateau());
    }

    /**
     * Creer un Joueur
     * @param pseudo Son pseudo
     * @param score Son score
     */
    public Joueur(String pseudo, int score){
        setPseudo(pseudo);
        setScore(score);
    }

//    private void writeObject(ObjectOutputStream stream){
//        try {
//            stream.defaultWriteObject();
//            stream.writeUTF(getPseudo());
//            stream.writeInt(getScore());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void readObject(ObjectInputStream stream){
//        initialisation();
//        try {
//            setPseudo(stream.readUTF());
//            setScore(stream.readInt());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Permet d'initialiser le pseudo et le score du joueur
     */
    private void initialisation(){
        setPseudo("");
        setScore(0);
    }


    /**
     * Test si le joueur a le meme pseudo qu'un joueur donné
     * @param o L'object a comparé
     * @return True si les deux joueur on le même pseudo, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return pseudo.equals(joueur.pseudo);
    }

    /**
     * Renvoi le pseudo du joueur
     * @return Le pseudo
     */
    @Override
    public String toString() {
        return this.getPseudo() + "\n" + "Score : " + this.getScore();
    }


    /**
     * on compare le joueur avec un joueur en paramètre
     * @param joueur joueur à comparer
     * @return valeur positive si le score est inférieur, 0 si égaux, négative si supérieur
     */
    @Override
    public int compareTo(Joueur joueur) {
        if(this.getScore() < joueur.getScore()){
            return 1;
        }
        else if(this.getScore() == joueur.getScore()){
            return 0;
        }
        return -1;
    }

    /**
     * Permet de retourner le pseudo du joueur
     * @return Retourne le pseudo du joueur
     */
    public String getPseudo(){
        return pseudo;
    }

    /**
     * Permet de mettre à jour le pseudo du joueur
     * @param pseudo Nouvelle valeur du pseudo du joueur
     */
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    /**
     * Permet de retourner le score du joueur
     * @return Retourne le score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Permet de mettre à jour le score du joueur
     * @param score Nouvelle valeur du score du joueur
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Permet de retourner l'image du joueur
     * @return Retourne l'image du joueur
     */
    public Bitmap getImageJoueur() {
        return imageJoueur;
    }

    /**
     * Permet de mettre à jour l'image du joueur
     * @param imageJoueur Nouvelle valeur de l'image du joueur
     */
    public void setImageJoueur(Bitmap imageJoueur) {
        this.imageJoueur = imageJoueur;
    }

    /**
     * Permet de retourner la valeur du plateau
     * @return Retourne la valeur du plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Permet de mettre à jour la valeur du plateau
     * @param plateau Nouvelle valeur du plateau
     */
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    /**
     * Permet de retourner le plateau de l'adversaire du joueur
     * @return Retourne le plateau de l'adversaire du joueur
     */
    public Plateau getPlateauAdverse() {
        return plateauAdverse;
    }

    /**
     * Permet de mettre à jour le plateau de l'adversaire du joueur
     * @param plateauAdverse Nouvelle valeur du plateau de l'adversaire du joueur
     */
    public void setPlateauAdverse(Plateau plateauAdverse) {
        this.plateauAdverse = plateauAdverse;
    }
}
