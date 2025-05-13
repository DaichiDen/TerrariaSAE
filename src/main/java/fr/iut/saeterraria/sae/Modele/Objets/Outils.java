package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Classe pour les sceau, canne à pèche, Coffres...
public class Outils {
    private StringProperty nom;

    public Outils (String nom) {
        this.nom = new SimpleStringProperty(nom);
    }
}
