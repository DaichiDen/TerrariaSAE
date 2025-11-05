package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Armes extends Equipement {
    private IntegerProperty attaque; // Valeur d'attaque de l'arme

    public Armes (String nom, String desc, int attaque, int codeobjet) {
        super(nom,desc,codeobjet);
        this.attaque = new SimpleIntegerProperty(attaque);
    }

    public int getAttaque(){ return attaque.getValue(); }



}
