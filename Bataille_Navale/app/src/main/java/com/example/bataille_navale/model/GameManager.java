package com.example.bataille_navale.model;

import java.util.ArrayList;

public class GameManager {

    private static GameManager instanceUnique;
    private Joueur j1;
    private Joueur j2;
    private Joueur joueurEnCours;
    private boolean aTouche;
    private boolean ajoue;

    /**
     * constructeur de manager
     */
    private GameManager(){
    }

    /**
     * Méthode qui vérifie qu'il n'y ai pas d'instance de GameManager déjà créée, si non alors elle l'instancie
     * @return le game manager unique ou non
     */
    public static GameManager getInstance() {
        if (instanceUnique == null) {
            instanceUnique = new GameManager();
        }
        return instanceUnique;
    }

    /**
     * méthode qui permet de lancer une nouvelle partie
     */
    public void lancerPartie() {
       j1 = new Joueur();
       j2 = new Joueur();
       setPlateauxAdverses();
       setJoueurEnCours(j1);
       setAjoue(false);
       setaTouche(false);
    }

    /**
     * permet aux joueurs de rejouer sans perdre leurs pseudos
     */
    public void rejouer() {
        resetPlateauxJoueurs();
        setPlateauxAdverses();
        setJoueurEnCours(j1);
        setAjoue(false);
        setaTouche(false);
    }

    /**
     * remet les plateaux des joueurs à 0
     */
    private void resetPlateauxJoueurs(){
        j1.setPlateau(new Plateau());
        j2.setPlateau(new Plateau());
        j1.setScore(0);
        j2.setScore(0);
    }

    /**
     * permet de mettre les plateaux adverses sur chaque joueur
     */
    private void setPlateauxAdverses() {
        j1.setPlateauAdverse(j2.getPlateau());
        j2.setPlateauAdverse(j1.getPlateau());
    }

    /**
     * change de tour dans la partie en cours
     */
    public void changementTour(){
        changementJoueur();
        setAjoue(false);
        setaTouche(false);
    }

    /**
     * change le joueur courant
     */
    public void changementJoueur(){
        if(getJoueurEnCours() == getJ1()){
            setJoueurEnCours(getJ2());
        }
        else {
            setJoueurEnCours(getJ1());
        }
    }

    /**
     * vérifie si la partie est finie
     * @return boolean
     */
    public boolean isPartieFinie(){
        return joueurEnCours.getPlateauAdverse().isPlateauCoule();
    }

    public Joueur getJoueurEnCours() {
        return joueurEnCours;
    }

    public void setJoueurEnCours(Joueur joueurEnCours) {
        this.joueurEnCours = joueurEnCours;
    }

    public Joueur getJ1() {
        return j1;
    }

    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    public boolean getaTouche() {
        return aTouche;
    }

    public void setaTouche(boolean aTouche) {
        this.aTouche = aTouche;
    }

    public boolean getajoue() {
        return ajoue;
    }

    public void setAjoue(boolean ajoue) {
        this.ajoue = ajoue;
    }
}
