package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Armes extends Equipement {
    private IntegerProperty attaque; // Valeur d'attaque de l'arme

    public Armes (String nom, String desc, int attaque, BlocConstruction blocConstruction) {
        super(nom,desc,blocConstruction);
        this.attaque = new SimpleIntegerProperty(attaque);
    }

    public Armes (String nom, String desc, int attaque) {
        super(nom,desc);
        this.attaque = new SimpleIntegerProperty(attaque);
    }

    public IntegerProperty attaqueProperty() { return attaque; }
    public int getAttaque(){ return attaque.getValue(); }

    public abstract void attaquer();

}
