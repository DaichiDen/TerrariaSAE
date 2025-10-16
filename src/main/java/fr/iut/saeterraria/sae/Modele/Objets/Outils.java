package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

// Classe pour les sceau, canne à pèche, Coffres...
public abstract class Outils extends Item{

    public Outils (String nom,String description, int codeobjet) {
        super(nom,description,3, codeobjet);
    }

    public Outils (String nom, String description, BlocConstruction blocConstruction, int codeobjet) {
        super(nom,description,3,blocConstruction,codeobjet);
    }



    public abstract void action();
}
