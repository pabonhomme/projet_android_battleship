package com.example.bataille_navale.model;

import java.util.Scanner;

public class Manager {

    private static Manager instanceUnique;
    private Partie partie;

    /**
     * constructeur de manager
     */
    private Manager(){
    }

    /**
     * Méthode qui vérifie qu'il n'y ai pas d'instance de GameManager déjà créée, si non alors elle l'instancie
     * @return le game manager unique ou non
     */
    public static Manager getInstance() {
        if (instanceUnique == null) {
            instanceUnique = new Manager();
        }
        return instanceUnique;
    }

    public void lancerPartie() {
        partie = new Partie();
//        boolean test = plateau.positionneBateau(1, 2, Orientation.HORIZONTAL, 2, plateau.navires[1]);
//        test();
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
//                if(plateau.estCoulee(ligne, colonne)){
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

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
