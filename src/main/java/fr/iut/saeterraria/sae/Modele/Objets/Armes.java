package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Armes extends Equipement {
    private DoubleProperty attaque; // Valeur d'attaque de l'arme

    public Armes (String nom, String desc, double attaque, BlocConstruction blocConstruction) {
        super(nom,desc,blocConstruction);
        this.attaque = new SimpleDoubleProperty(attaque);
    }

    public DoubleProperty attaqueProperty() { return attaque; }
    public double getAttaque(){ return attaque.getValue(); }

    public abstract void attaquer();

}
