package com.example.bataille_navale.manager;

import com.example.bataille_navale.model.Joueur;
import com.example.bataille_navale.model.Partie;
import com.example.bataille_navale.model.Plateau;
import com.example.bataille_navale.persistance.Sauveur;
import com.example.bataille_navale.persistance.ser.SauveurSer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe qui permet de gérer l'application
 */
public class GameManager {

    private static GameManager instanceUnique;
    private final Sauveur leSauveur = new SauveurSer();
    private Joueur j1;
    private Joueur j2;
    private Joueur joueurEnCours;
    private boolean aTouche;
    private boolean aJoue;
    private Partie partieEnCours;
    private List<Partie> historique = new ArrayList<>();

    /**
     * Nom du fichier dans lequel on sauvegarde l'historique
     */
    public static final String NAME_FILE = "historique_parties";

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
     * permet de charger les données à partir d'un fichier
     */
    public void chargerDonnees(FileInputStream file){
        historique = leSauveur.chargerStats(file);
    }

    /**
     * permet de sauvegarder les données dans un fichier
     */
    public void sauvegarderDonnees(FileOutputStream file){
        leSauveur.sauvegarderStats(historique, file);
    }

    /**
     * méthode qui permet de lancer une nouvelle partie
     */
    public void lancerPartie() {
       setPartieEnCours(new Partie());
       setJ1(new Joueur());
       setJ2(new Joueur());
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
        if(joueurEnCours.getPlateauAdverse().isPlateauCoule()){
            partieEnCours.setjGagnant(getJoueurEnCours());
            if(getJoueurEnCours() == j1){
                partieEnCours.setjPerdant(j2);
            }
            else{
                partieEnCours.setjPerdant(j1);
            }
            partieEnCours.mettreScoreAJour();
            historique.add(partieEnCours);
            return true;
        }
        return false;
    }

    /**
     * Supprime une partie de l'historique
     * @param id UUID
     */
    public void suppressionPartieHistorique(UUID id) {
        Partie partieASupp = null;
        for (Partie partie:historique) {
            if(partie.getId() == id){
                partieASupp = partie;
                break;
            }
        }
        historique.remove(partieASupp);
    }

    /**
     * Permet de retourner le joueur en cours
     * @return Retourne le joueur en cours
     */
    public Joueur getJoueurEnCours() {
        return joueurEnCours;
    }

    /**
     * Permet de mettre à jour le joueur en cours
     * @param joueurEnCours Nouveau joueur en cours
     */
    public void setJoueurEnCours(Joueur joueurEnCours) {
        this.joueurEnCours = joueurEnCours;
    }

    /**
     * Permet de retourner le joueur 1
     * @return Retourne le joueur 1
     */
    public Joueur getJ1() {
        return j1;
    }

    /**
     * Permet de mettre à jour le joueur 1
     * @param j1 Nouveau joueur 1
     */
    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    /**
     * Permet de retourner le joueur 2
     * @return Retourne le joueur 2
     */
    public Joueur getJ2() {
        return j2;
    }

    /**
     * Permet de mettre à jour le joueur 2
     * @param j2 Nouveau joueur 2
     */
    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    /**
     * Permet de retourner si le joueur en cours à touché un bateau
     * @return Retourne true si le joueur en cours à touché un bateau, ou false sinon
     */
    public boolean getaTouche() {
        return aTouche;
    }

    /**
     * Permet de mettre à jour si le joueur en cours à touché un bateau
     * @param aTouche Nouvelle valeur de la variable qui contient si le joueur en cours à touché un bateau
     */
    public void setaTouche(boolean aTouche) {
        this.aTouche = aTouche;
    }

    /**
     * Permet de retourner si le joueur a joué
     * @return Retourne truc si le joueur a joué, ou false sinon
     */
    public boolean getaJoue() {
        return aJoue;
    }

    /**
     * Permet de mettre à jour si le joueur a joué
     * @param aJoue Nouvelle valeur de la variable qui permet de savoir si le joueur a joué
     */
    public void setAjoue(boolean aJoue) {
        this.aJoue = aJoue;
    }

    /**
     * Permet de retourner l'historique
     * @return Retourne l'historique
     */
    public List<Partie> getHistorique() {
        return historique;
    }

    /**
     * Permet de mettre à jour l'historique
     * @param historique Nouvelle valeur de l'historique
     */
    public void setHistorique(List<Partie> historique) {
        this.historique = historique;
    }

    /**
     * Permet de retourner la partie en cours
     * @return Retourne la partie en cours
     */
    public Partie getPartieEnCours() {
        return partieEnCours;
    }

    /**
     * Permet de modifier la valeur de la partie en cours
     * @param partieEnCours Nouvelle valeur de la partie en cours
     */
    public void setPartieEnCours(Partie partieEnCours) {
        this.partieEnCours = partieEnCours;
    }
}
