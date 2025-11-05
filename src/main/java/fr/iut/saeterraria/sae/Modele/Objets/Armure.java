package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Entites.Joueur;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract  class Armure extends Equipement {
    private DoubleProperty defense; // Valeur de défense de l'armure





    public Armure(String nom, String desc, double defense, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc, blocConstruction, codeobjet);

        this.defense = new SimpleDoubleProperty(defense);
    }

    public DoubleProperty defenseProperty() {
        return defense;
    }

    public double getDefense() {
        return defense.getValue();
    }

    public abstract int getTypeArmure();

    public abstract int getCaseEquipement();

}
