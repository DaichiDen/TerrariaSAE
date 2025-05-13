package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Armes extends Equipement {
    private DoubleProperty attaque; // Valeur d'attaque de l'arme

    public Armes (String nom, String desc, double attaque) {
        super(nom,desc);
        this.attaque = new SimpleDoubleProperty(attaque);
    }

    public DoubleProperty attaqueProperty() { return attaque; }
    public double getAttaque(){ return attaque.getValue(); }

}
