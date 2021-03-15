package com.example.bataille_navale.model;

import java.io.Serializable;

public class Partie implements Serializable {

    Joueur jGagnant;
    Joueur jPerdant;

    public Partie() {
    }

    public Partie(Joueur jGagnant, Joueur jPerdant) {
        setjGagnant(jGagnant);
        setjPerdant(jPerdant);
    }

    public Joueur getjGagnant() {
        return jGagnant;
    }

    public void setjGagnant(Joueur jGagnant) {
        this.jGagnant = jGagnant;
    }
    public Joueur getjPerdant() {
        return jPerdant;
    }

    public void setjPerdant(Joueur jPerdant) {
        this.jPerdant = jPerdant;
    }
}
