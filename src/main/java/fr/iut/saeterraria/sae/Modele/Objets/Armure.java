package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Armure extends Equipement {
    private DoubleProperty defense; // Valeur de défense de l'armure

    public Armure (String nom, String desc, double defense, BlocConstruction blocConstruction) {
        super(nom,desc,blocConstruction);
        this.defense = new SimpleDoubleProperty(defense);
    }

    public DoubleProperty defenseProperty() { return defense; }
    public double getDefense(){ return defense.getValue(); }
}