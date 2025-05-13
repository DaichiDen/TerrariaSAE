package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PNJ {
    private StringProperty nom;

    public PNJ(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }
}