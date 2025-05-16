package fr.iut.saeterraria.sae.Modele.Objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Bloc extends Item{
    private StringProperty nom;
    private int width, height;


    public Bloc (String nom,String description) {
        super(nom,description,1);
    }

}
