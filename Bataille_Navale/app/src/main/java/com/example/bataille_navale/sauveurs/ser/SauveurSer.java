package com.example.bataille_navale.sauveurs.ser;

import com.example.bataille_navale.model.Joueur;
import com.example.bataille_navale.model.Partie;
import com.example.bataille_navale.sauveurs.Sauveur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Permet la serialization d'une collection de Partie dans un fichier
 */
public class SauveurSer extends Sauveur {

    /**
     * Sauvegarde une collection de Partie
     * @param historique  La collection a sauvegarder
     */
    @Override
    public void sauvegarderStats(List<Partie> historique, FileOutputStream file) {
        serialiser(historique, file);
    }

    /**
     * Charge une collection de Partie
     * @return  La collection de Partie
     */
    @Override
    public List<Partie> chargerStats(FileInputStream file) {
        return deserialiser(file);
    }


    /**
     * sérialise la liste de Partie
     * @param historique Collection<Joueur>
     */
    private void serialiser(List<Partie> historique, FileOutputStream file) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(file);
            oout.writeObject(historique);
            oout.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * désérialise la liste de Partie
     * @return List<Partie>
     */
    private List<Partie> deserialiser(FileInputStream file) {
        List<Partie> historique = new ArrayList<Partie>();
        try {
            ObjectInputStream oin = new ObjectInputStream(file);
            historique = (ArrayList<Partie>) oin.readObject();
            oin.close();
        } catch (ClassNotFoundException | IOException nfe) {
            nfe.printStackTrace();
        }
        return historique;
    }

}