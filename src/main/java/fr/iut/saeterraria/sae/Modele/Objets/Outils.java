package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

// Classe pour les sceau, canne à pèche, Coffres...
public abstract class Outils extends Item{

    public Outils (String nom,String description) {
        super(nom,description,3);
    }

    public abstract void action();
}
