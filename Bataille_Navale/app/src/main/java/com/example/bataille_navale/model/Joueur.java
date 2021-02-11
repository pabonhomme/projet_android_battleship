package com.example.bataille_navale.model;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Joueur implements Comparable<Joueur>, Serializable {

    private String pseudo;
    public String getPseudo(){ return pseudo; }
    public void setPseudo(String pseudo){this.pseudo = pseudo; }

    private int score;
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    /**
     * Creer un Joueur
     */
    public Joueur(){
        setPseudo("");
        setScore(0);
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

    private void writeObject(ObjectOutputStream stream){
        try {
            stream.defaultWriteObject();
            stream.writeUTF(getPseudo());
            stream.writeInt(getScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream stream){
        initialisation();
        try {
            setPseudo(stream.readUTF());
            setScore(stream.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
