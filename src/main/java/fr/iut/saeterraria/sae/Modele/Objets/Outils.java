package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

// Classe pour les sceau, canne à pèche, Coffres...
public abstract class Outils extends Item{
    private int efficacite;
    public Outils (String nom,String description, int efficacite) {
        super(nom,description,3);
        efficacite = efficacite;
    }

    public Outils (String nom, String description, int efficacite, BlocConstruction blocConstruction) {
        super(nom,description,3,blocConstruction);
        efficacite = efficacite;
    }

    public int getEfficacite(){ return efficacite;}
    public abstract void action();
}
