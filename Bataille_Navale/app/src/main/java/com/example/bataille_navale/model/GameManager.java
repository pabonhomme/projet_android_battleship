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
       setPlateauxAdverses(j1,j2);
       setJoueurEnCours(j1);
       setAjoue(false);
       setaTouche(false);
    }

    public boolean isPartieFinie(){
        return joueurEnCours.getPlateauAdverse().isPlateauCoule();
    }

    /**
     * permet de mettre les plateauw adverses sur chaque joueur
     * @param j1 Joueur 1
     * @param j2 Joueur 2
     */
    private void setPlateauxAdverses(Joueur j1, Joueur j2) {
        j1.setPlateauAdverse(j2.getPlateau());
        j2.setPlateauAdverse(j1.getPlateau());
    }

    public void changementJoueur(){
        if(getJoueurEnCours() == getJ1()){
            setJoueurEnCours(getJ2());
        }
        else {
            setJoueurEnCours(getJ1());
        }
    }

    public void changementTour(){
        changementJoueur();
        setAjoue(false);
        setaTouche(false);
    }

    public void test() {
//        System.out.print(plateau);
//        Scanner k = new Scanner(System.in); // je n'ai pas réussi à faire de test en mode console, j'aurai bien aimé
//        System.out.println(plateau);
//        while(!plateau.etatsBateaux()){
//
//            int ligne, colonne;
//            System.out.print("Entrez la ligne suivie de la colonne : ");
//            ligne = 5;
//            colonne = 6;
//            plateau.visite(ligne, colonne);
//            if(plateau.faitPartieDeBateau(ligne, colonne)){
//                if(plateau.estTouchee(ligne, colonne)){
//                    System.out.println("Bâteau détruit !");
//                } else{
//                    System.out.println("Bâteau endommagé !");
//                }
//                System.out.println(plateau);
//            } else {
//                System.out.println("Vide !");
//                System.out.println(plateau);
//            }
//        }

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
