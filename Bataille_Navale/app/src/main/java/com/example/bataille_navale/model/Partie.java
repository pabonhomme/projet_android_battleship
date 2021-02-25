package com.example.bataille_navale.model;

public class Partie {

    private Plateau plateauJ1 = new Plateau();
    private Plateau plateauJ2 = new Plateau();

    public Partie(){
        plateauJ1 = new Plateau();
        plateauJ2 = new Plateau();
    }

    public Plateau getPlateauJ1() {
        return plateauJ1;
    }

    public void setPlateauJ1(Plateau plateauJ1) {
        this.plateauJ1 = plateauJ1;
    }

    public Plateau getPlateauJ2() {
        return plateauJ2;
    }

    public void setPlateauJ2(Plateau plateauJ2) {
        this.plateauJ2 = plateauJ2;
    }
}
