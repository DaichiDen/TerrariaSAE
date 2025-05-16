package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Classe pour les sceau, canne à pèche, Coffres...
public class Outils extends Item{
    private StringProperty nom;

    public Outils (String nom,String description) {
        super(nom,description,3);
    }
}
